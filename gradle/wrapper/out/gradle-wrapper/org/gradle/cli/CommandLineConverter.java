package org.gradle.cli;

import org.gradle.cli.CommandLineArgumentException;
import org.gradle.cli.CommandLineParser;
import org.gradle.cli.ParsedCommandLine;

public interface CommandLineConverter {

   Object convert(Iterable var1, Object var2) throws CommandLineArgumentException;

   Object convert(ParsedCommandLine var1, Object var2) throws CommandLineArgumentException;

   void configure(CommandLineParser var1);
}
