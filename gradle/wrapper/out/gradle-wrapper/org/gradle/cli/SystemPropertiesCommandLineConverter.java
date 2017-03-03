package org.gradle.cli;

import org.gradle.cli.AbstractPropertiesCommandLineConverter;

public class SystemPropertiesCommandLineConverter extends AbstractPropertiesCommandLineConverter {

   protected String getPropertyOption() {
      return "D";
   }

   protected String getPropertyOptionDetailed() {
      return "system-prop";
   }

   protected String getPropertyOptionDescription() {
      return "Set system property of the JVM (e.g. -Dmyprop=myvalue).";
   }
}
