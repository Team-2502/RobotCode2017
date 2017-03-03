package org.gradle.wrapper;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Properties;
import org.gradle.cli.CommandLineParser;
import org.gradle.cli.ParsedCommandLine;
import org.gradle.cli.SystemPropertiesCommandLineConverter;
import org.gradle.wrapper.BootstrapMainStarter;
import org.gradle.wrapper.Download;
import org.gradle.wrapper.GradleUserHomeLookup;
import org.gradle.wrapper.Install;
import org.gradle.wrapper.PathAssembler;
import org.gradle.wrapper.SystemPropertiesHandler;
import org.gradle.wrapper.WrapperExecutor;

public class GradleWrapperMain {

   public static final String GRADLE_USER_HOME_OPTION = "g";
   public static final String GRADLE_USER_HOME_DETAILED_OPTION = "gradle-user-home";


   public static void main(String[] args) throws Exception {
      File wrapperJar = wrapperJar();
      File propertiesFile = wrapperProperties(wrapperJar);
      File rootDir = rootDir(wrapperJar);
      CommandLineParser parser = new CommandLineParser();
      parser.allowUnknownOptions();
      parser.option(new String[]{"g", "gradle-user-home"}).hasArgument();
      SystemPropertiesCommandLineConverter converter = new SystemPropertiesCommandLineConverter();
      converter.configure(parser);
      ParsedCommandLine options = parser.parse(args);
      Properties systemProperties = System.getProperties();
      systemProperties.putAll(converter.convert(options, new HashMap()));
      File gradleUserHome = gradleUserHome(options);
      addSystemProperties(gradleUserHome, rootDir);
      WrapperExecutor wrapperExecutor = WrapperExecutor.forWrapperPropertiesFile(propertiesFile, System.out);
      wrapperExecutor.execute(args, new Install(new Download("gradlew", wrapperVersion()), new PathAssembler(gradleUserHome)), new BootstrapMainStarter());
   }

   private static void addSystemProperties(File gradleHome, File rootDir) {
      System.getProperties().putAll(SystemPropertiesHandler.getSystemProperties(new File(gradleHome, "gradle.properties")));
      System.getProperties().putAll(SystemPropertiesHandler.getSystemProperties(new File(rootDir, "gradle.properties")));
   }

   private static File rootDir(File wrapperJar) {
      return wrapperJar.getParentFile().getParentFile().getParentFile();
   }

   private static File wrapperProperties(File wrapperJar) {
      return new File(wrapperJar.getParent(), wrapperJar.getName().replaceFirst("\\.jar$", ".properties"));
   }

   private static File wrapperJar() {
      URI location;
      try {
         location = GradleWrapperMain.class.getProtectionDomain().getCodeSource().getLocation().toURI();
      } catch (URISyntaxException var2) {
         throw new RuntimeException(var2);
      }

      if(!location.getScheme().equals("file")) {
         throw new RuntimeException(String.format("Cannot determine classpath for wrapper Jar from codebase \'%s\'.", new Object[]{location}));
      } else {
         return new File(location.getPath());
      }
   }

   static String wrapperVersion() {
      try {
         InputStream e = GradleWrapperMain.class.getResourceAsStream("/build-receipt.properties");
         if(e == null) {
            throw new RuntimeException("No build receipt resource found.");
         } else {
            Properties buildReceipt = new Properties();

            String var3;
            try {
               buildReceipt.load(e);
               String versionNumber = buildReceipt.getProperty("versionNumber");
               if(versionNumber == null) {
                  throw new RuntimeException("No version number specified in build receipt resource.");
               }

               var3 = versionNumber;
            } finally {
               e.close();
            }

            return var3;
         }
      } catch (Exception var8) {
         throw new RuntimeException("Could not determine wrapper version.", var8);
      }
   }

   private static File gradleUserHome(ParsedCommandLine options) {
      return options.hasOption("g")?new File(options.option("g").getValue()):GradleUserHomeLookup.gradleUserHome();
   }
}
