package org.gradle.wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Properties;
import org.gradle.wrapper.BootstrapMainStarter;
import org.gradle.wrapper.Install;
import org.gradle.wrapper.WrapperConfiguration;

public class WrapperExecutor {

   public static final String DISTRIBUTION_URL_PROPERTY = "distributionUrl";
   public static final String DISTRIBUTION_BASE_PROPERTY = "distributionBase";
   public static final String ZIP_STORE_BASE_PROPERTY = "zipStoreBase";
   public static final String DISTRIBUTION_PATH_PROPERTY = "distributionPath";
   public static final String ZIP_STORE_PATH_PROPERTY = "zipStorePath";
   private final Properties properties;
   private final File propertiesFile;
   private final Appendable warningOutput;
   private final WrapperConfiguration config = new WrapperConfiguration();


   public static WrapperExecutor forProjectDirectory(File projectDir, Appendable warningOutput) {
      return new WrapperExecutor(new File(projectDir, "gradle/wrapper/gradle-wrapper.properties"), new Properties(), warningOutput);
   }

   public static WrapperExecutor forWrapperPropertiesFile(File propertiesFile, Appendable warningOutput) {
      if(!propertiesFile.exists()) {
         throw new RuntimeException(String.format("Wrapper properties file \'%s\' does not exist.", new Object[]{propertiesFile}));
      } else {
         return new WrapperExecutor(propertiesFile, new Properties(), warningOutput);
      }
   }

   WrapperExecutor(File propertiesFile, Properties properties, Appendable warningOutput) {
      this.properties = properties;
      this.propertiesFile = propertiesFile;
      this.warningOutput = warningOutput;
      if(propertiesFile.exists()) {
         try {
            loadProperties(propertiesFile, properties);
            this.config.setDistribution(this.prepareDistributionUri());
            this.config.setDistributionBase(this.getProperty("distributionBase", this.config.getDistributionBase()));
            this.config.setDistributionPath(this.getProperty("distributionPath", this.config.getDistributionPath()));
            this.config.setZipBase(this.getProperty("zipStoreBase", this.config.getZipBase()));
            this.config.setZipPath(this.getProperty("zipStorePath", this.config.getZipPath()));
         } catch (Exception var5) {
            throw new RuntimeException(String.format("Could not load wrapper properties from \'%s\'.", new Object[]{propertiesFile}), var5);
         }
      }

   }

   private URI prepareDistributionUri() throws URISyntaxException {
      URI source = this.readDistroUrl();
      return source.getScheme() == null?(new File(this.propertiesFile.getParentFile(), source.getSchemeSpecificPart())).toURI():source;
   }

   private URI readDistroUrl() throws URISyntaxException {
      return this.properties.getProperty("distributionUrl") != null?new URI(this.getProperty("distributionUrl")):this.readDistroUrlDeprecatedWay();
   }

   private URI readDistroUrlDeprecatedWay() throws URISyntaxException {
      String distroUrl = null;

      try {
         distroUrl = this.getProperty("urlRoot") + "/" + this.getProperty("distributionName") + "-" + this.getProperty("distributionVersion") + "-" + this.getProperty("distributionClassifier") + ".zip";
         Formatter e = new Formatter();
         e.format("Wrapper properties file \'%s\' contains deprecated entries \'urlRoot\', \'distributionName\', \'distributionVersion\' and \'distributionClassifier\'. These will be removed soon. Please use \'%s\' instead.%n", new Object[]{this.propertiesFile, "distributionUrl"});
         this.warningOutput.append(e.toString());
      } catch (Exception var3) {
         this.reportMissingProperty("distributionUrl");
      }

      return new URI(distroUrl);
   }

   private static void loadProperties(File propertiesFile, Properties properties) throws IOException {
      FileInputStream inStream = new FileInputStream(propertiesFile);

      try {
         properties.load(inStream);
      } finally {
         inStream.close();
      }

   }

   public URI getDistribution() {
      return this.config.getDistribution();
   }

   public WrapperConfiguration getConfiguration() {
      return this.config;
   }

   public void execute(String[] args, Install install, BootstrapMainStarter bootstrapMainStarter) throws Exception {
      File gradleHome = install.createDist(this.config);
      bootstrapMainStarter.start(args, gradleHome);
   }

   private String getProperty(String propertyName) {
      return this.getProperty(propertyName, (String)null);
   }

   private String getProperty(String propertyName, String defaultValue) {
      String value = this.properties.getProperty(propertyName);
      return value != null?value:(defaultValue != null?defaultValue:this.reportMissingProperty(propertyName));
   }

   private String reportMissingProperty(String propertyName) {
      throw new RuntimeException(String.format("No value with key \'%s\' specified in wrapper properties file \'%s\'.", new Object[]{propertyName, this.propertiesFile}));
   }
}
