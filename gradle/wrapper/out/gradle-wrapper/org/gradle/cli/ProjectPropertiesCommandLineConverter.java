package org.gradle.cli;

import org.gradle.cli.AbstractPropertiesCommandLineConverter;

public class ProjectPropertiesCommandLineConverter extends AbstractPropertiesCommandLineConverter {

   protected String getPropertyOption() {
      return "P";
   }

   protected String getPropertyOptionDetailed() {
      return "project-prop";
   }

   protected String getPropertyOptionDescription() {
      return "Set project property for the build script (e.g. -Pmyprop=myvalue).";
   }
}
