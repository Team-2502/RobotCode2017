package org.gradle.cli;


public class CommandLineArgumentException extends RuntimeException {

   public CommandLineArgumentException(String message) {
      super(message);
   }

   public CommandLineArgumentException(String message, Throwable cause) {
      super(message, cause);
   }
}
