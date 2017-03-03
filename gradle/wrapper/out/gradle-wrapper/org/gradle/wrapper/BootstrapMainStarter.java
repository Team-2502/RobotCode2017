package org.gradle.wrapper;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class BootstrapMainStarter {

   public void start(String[] args, File gradleHome) throws Exception {
      File gradleJar = this.findLauncherJar(gradleHome);
      URLClassLoader contextClassLoader = new URLClassLoader(new URL[]{gradleJar.toURI().toURL()}, ClassLoader.getSystemClassLoader().getParent());
      Thread.currentThread().setContextClassLoader(contextClassLoader);
      Class mainClass = contextClassLoader.loadClass("org.gradle.launcher.GradleMain");
      Method mainMethod = mainClass.getMethod("main", new Class[]{String[].class});
      mainMethod.invoke((Object)null, new Object[]{args});
   }

   private File findLauncherJar(File gradleHome) {
      File[] arr$ = (new File(gradleHome, "lib")).listFiles();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         File file = arr$[i$];
         if(file.getName().matches("gradle-launcher-.*\\.jar")) {
            return file;
         }
      }

      throw new RuntimeException(String.format("Could not locate the Gradle launcher JAR in Gradle distribution \'%s\'.", new Object[]{gradleHome}));
   }
}
