package org.gradle.wrapper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import org.gradle.wrapper.IDownload;
import org.gradle.wrapper.Download.1;
import org.gradle.wrapper.Download.SystemPropertiesProxyAuthenticator;

public class Download implements IDownload {

   private static final int PROGRESS_CHUNK = 20000;
   private static final int BUFFER_SIZE = 10000;
   private final String applicationName;
   private final String applicationVersion;


   public Download(String applicationName, String applicationVersion) {
      this.applicationName = applicationName;
      this.applicationVersion = applicationVersion;
      this.configureProxyAuthentication();
   }

   private void configureProxyAuthentication() {
      if(System.getProperty("http.proxyUser") != null) {
         Authenticator.setDefault(new SystemPropertiesProxyAuthenticator((1)null));
      }

   }

   public void download(URI address, File destination) throws Exception {
      destination.getParentFile().mkdirs();
      this.downloadInternal(address, destination);
   }

   private void downloadInternal(URI address, File destination) throws Exception {
      BufferedOutputStream out = null;
      InputStream in = null;

      try {
         URL url = address.toURL();
         out = new BufferedOutputStream(new FileOutputStream(destination));
         URLConnection conn = url.openConnection();
         String userAgentValue = this.calculateUserAgent();
         conn.setRequestProperty("User-Agent", userAgentValue);
         in = conn.getInputStream();
         byte[] buffer = new byte[10000];

         int numRead;
         for(long progressCounter = 0L; (numRead = in.read(buffer)) != -1; out.write(buffer, 0, numRead)) {
            progressCounter += (long)numRead;
            if(progressCounter / 20000L > 0L) {
               System.out.print(".");
               progressCounter -= 20000L;
            }
         }
      } finally {
         System.out.println("");
         if(in != null) {
            in.close();
         }

         if(out != null) {
            out.close();
         }

      }

   }

   private String calculateUserAgent() {
      String appVersion = this.applicationVersion;
      String javaVendor = System.getProperty("java.vendor");
      String javaVersion = System.getProperty("java.version");
      String javaVendorVersion = System.getProperty("java.vm.version");
      String osName = System.getProperty("os.name");
      String osVersion = System.getProperty("os.version");
      String osArch = System.getProperty("os.arch");
      return String.format("%s/%s (%s;%s;%s) (%s;%s;%s)", new Object[]{this.applicationName, appVersion, osName, osVersion, osArch, javaVendor, javaVersion, javaVendorVersion});
   }
}
