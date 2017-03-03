package org.gradle.wrapper;

import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import org.gradle.wrapper.WrapperConfiguration;

public class PathAssembler {

   public static final String GRADLE_USER_HOME_STRING = "GRADLE_USER_HOME";
   public static final String PROJECT_STRING = "PROJECT";
   private File gradleUserHome;


   public PathAssembler() {}

   public PathAssembler(File gradleUserHome) {
      this.gradleUserHome = gradleUserHome;
   }

   public PathAssembler.LocalDistribution getDistribution(WrapperConfiguration configuration) {
      String baseName = this.getDistName(configuration.getDistribution());
      String distName = this.removeExtension(baseName);
      String rootDirName = this.rootDirName(distName, configuration);
      File distDir = new File(this.getBaseDir(configuration.getDistributionBase()), configuration.getDistributionPath() + "/" + rootDirName);
      File distZip = new File(this.getBaseDir(configuration.getZipBase()), configuration.getZipPath() + "/" + rootDirName + "/" + baseName);
      return new PathAssembler.LocalDistribution(distDir, distZip);
   }

   private String rootDirName(String distName, WrapperConfiguration configuration) {
      String urlHash = this.getHash(configuration.getDistribution().toString());
      return String.format("%s/%s", new Object[]{distName, urlHash});
   }

   private String getHash(String string) {
      try {
         MessageDigest e = MessageDigest.getInstance("MD5");
         byte[] bytes = string.getBytes();
         e.update(bytes);
         return (new BigInteger(1, e.digest())).toString(36);
      } catch (Exception var4) {
         throw new RuntimeException("Could not hash input string.", var4);
      }
   }

   private String removeExtension(String name) {
      int p = name.lastIndexOf(".");
      return p < 0?name:name.substring(0, p);
   }

   private String getDistName(URI distUrl) {
      String path = distUrl.getPath();
      int p = path.lastIndexOf("/");
      return p < 0?path:path.substring(p + 1);
   }

   private File getBaseDir(String base) {
      if(base.equals("GRADLE_USER_HOME")) {
         return this.gradleUserHome;
      } else if(base.equals("PROJECT")) {
         return new File(System.getProperty("user.dir"));
      } else {
         throw new RuntimeException("Base: " + base + " is unknown");
      }
   }

   public class LocalDistribution {

      private final File distZip;
      private final File distDir;


      public LocalDistribution(File distDir, File distZip) {
         this.distDir = distDir;
         this.distZip = distZip;
      }

      public File getDistributionDir() {
         return this.distDir;
      }

      public File getZipFile() {
         return this.distZip;
      }
   }
}
