package org.gradle.wrapper;

import java.io.File;

public class GradleUserHomeLookup {

   public static final String DEFAULT_GRADLE_USER_HOME = System.getProperty("user.home") + "/.gradle";
   public static final String GRADLE_USER_HOME_PROPERTY_KEY = "gradle.user.home";
   public static final String GRADLE_USER_HOME_ENV_KEY = "GRADLE_USER_HOME";


   public static File gradleUserHome() {
      String gradleUserHome;
      return (gradleUserHome = System.getProperty("gradle.user.home")) != null?new File(gradleUserHome):((gradleUserHome = System.getenv("GRADLE_USER_HOME")) != null?new File(gradleUserHome):new File(DEFAULT_GRADLE_USER_HOME));
   }

}
