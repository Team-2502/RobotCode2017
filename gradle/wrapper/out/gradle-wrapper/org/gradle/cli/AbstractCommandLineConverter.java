package org.gradle.cli;

import org.gradle.cli.CommandLineArgumentException;
import org.gradle.cli.CommandLineConverter;
import org.gradle.cli.CommandLineParser;

public abstract class AbstractCommandLineConverter implements CommandLineConverter {

   public Object convert(Iterable args, Object target) throws CommandLineArgumentException {
      CommandLineParser parser = new CommandLineParser();
      this.configure(parser);
      return this.convert(parser.parse(args), target);
   }
}
