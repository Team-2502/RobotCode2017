package org.gradle.wrapper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.gradle.wrapper.ExclusiveFileAccessManager;
import org.gradle.wrapper.IDownload;
import org.gradle.wrapper.PathAssembler;
import org.gradle.wrapper.WrapperConfiguration;
import org.gradle.wrapper.Install.1;
import org.gradle.wrapper.PathAssembler.LocalDistribution;

public class Install {

   public static final String DEFAULT_DISTRIBUTION_PATH = "wrapper/dists";
   private final IDownload download;
   private final PathAssembler pathAssembler;
   private final ExclusiveFileAccessManager exclusiveFileAccessManager = new ExclusiveFileAccessManager(120000, 200);


   public Install(IDownload download, PathAssembler pathAssembler) {
      this.download = download;
      this.pathAssembler = pathAssembler;
   }

   public File createDist(WrapperConfiguration configuration) throws Exception {
      URI distributionUrl = configuration.getDistribution();
      LocalDistribution localDistribution = this.pathAssembler.getDistribution(configuration);
      File distDir = localDistribution.getDistributionDir();
      File localZipFile = localDistribution.getZipFile();
      return (File)this.exclusiveFileAccessManager.access(localZipFile, new 1(this, localZipFile, distDir, distributionUrl));
   }

   private File getDistributionRoot(File distDir, String distributionDescription) {
      List dirs = this.listDirs(distDir);
      if(dirs.isEmpty()) {
         throw new RuntimeException(String.format("Gradle distribution \'%s\' does not contain any directories. Expected to find exactly 1 directory.", new Object[]{distributionDescription}));
      } else if(dirs.size() != 1) {
         throw new RuntimeException(String.format("Gradle distribution \'%s\' contains too many directories. Expected to find exactly 1 directory.", new Object[]{distributionDescription}));
      } else {
         return (File)dirs.get(0);
      }
   }

   private List listDirs(File distDir) {
      ArrayList dirs = new ArrayList();
      if(distDir.exists()) {
         File[] arr$ = distDir.listFiles();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            File file = arr$[i$];
            if(file.isDirectory()) {
               dirs.add(file);
            }
         }
      }

      return dirs;
   }

   private void setExecutablePermissions(File gradleHome) {
      if(!this.isWindows()) {
         File gradleCommand = new File(gradleHome, "bin/gradle");
         String errorMessage = null;

         try {
            ProcessBuilder e = new ProcessBuilder(new String[]{"chmod", "755", gradleCommand.getCanonicalPath()});
            Process p = e.start();
            if(p.waitFor() == 0) {
               System.out.println("Set executable permissions for: " + gradleCommand.getAbsolutePath());
            } else {
               BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
               Formatter stdout = new Formatter();

               String line;
               while((line = is.readLine()) != null) {
                  stdout.format("%s%n", new Object[]{line});
               }

               errorMessage = stdout.toString();
            }
         } catch (IOException var9) {
            errorMessage = var9.getMessage();
         } catch (InterruptedException var10) {
            errorMessage = var10.getMessage();
         }

         if(errorMessage != null) {
            System.out.println("Could not set executable permissions for: " + gradleCommand.getAbsolutePath());
            System.out.println("Please do this manually if you want to use the Gradle UI.");
         }

      }
   }

   private boolean isWindows() {
      String osName = System.getProperty("os.name").toLowerCase(Locale.US);
      return osName.indexOf("windows") > -1;
   }

   private boolean deleteDir(File dir) {
      if(dir.isDirectory()) {
         String[] children = dir.list();

         for(int i = 0; i < children.length; ++i) {
            boolean success = this.deleteDir(new File(dir, children[i]));
            if(!success) {
               return false;
            }
         }
      }

      return dir.delete();
   }

   private void unzip(File zip, File dest) throws IOException {
      ZipFile zipFile = new ZipFile(zip);

      try {
         Enumeration entries = zipFile.entries();

         while(entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            if(entry.isDirectory()) {
               (new File(dest, entry.getName())).mkdirs();
            } else {
               BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(dest, entry.getName())));

               try {
                  this.copyInputStream(zipFile.getInputStream(entry), outputStream);
               } finally {
                  outputStream.close();
               }
            }
         }
      } finally {
         zipFile.close();
      }

   }

   private void copyInputStream(InputStream in, OutputStream out) throws IOException {
      byte[] buffer = new byte[1024];

      int len;
      while((len = in.read(buffer)) >= 0) {
         out.write(buffer, 0, len);
      }

      in.close();
      out.close();
   }

   // $FF: synthetic method
   static File access$000(Install x0, File x1, String x2) {
      return x0.getDistributionRoot(x1, x2);
   }

   // $FF: synthetic method
   static IDownload access$100(Install x0) {
      return x0.download;
   }

   // $FF: synthetic method
   static List access$200(Install x0, File x1) {
      return x0.listDirs(x1);
   }

   // $FF: synthetic method
   static boolean access$300(Install x0, File x1) {
      return x0.deleteDir(x1);
   }

   // $FF: synthetic method
   static void access$400(Install x0, File x1, File x2) throws IOException {
      x0.unzip(x1, x2);
   }

   // $FF: synthetic method
   static void access$500(Install x0, File x1) {
      x0.setExecutablePermissions(x1);
   }
}
