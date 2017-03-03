package org.gradle.cli;

import java.util.Iterator;
import java.util.Map;
import org.gradle.cli.AbstractCommandLineConverter;
import org.gradle.cli.CommandLineArgumentException;
import org.gradle.cli.CommandLineOption;
import org.gradle.cli.CommandLineParser;
import org.gradle.cli.ParsedCommandLine;

public abstract class AbstractPropertiesCommandLineConverter extends AbstractCommandLineConverter {

   protected abstract String getPropertyOption();

   protected abstract String getPropertyOptionDetailed();

   protected abstract String getPropertyOptionDescription();

   public void configure(CommandLineParser parser) {
      CommandLineOption option = parser.option(new String[]{this.getPropertyOption(), this.getPropertyOptionDetailed()});
      option = option.hasArguments();
      option.hasDescription(this.getPropertyOptionDescription());
   }

   public Map convert(ParsedCommandLine options, Map properties) throws CommandLineArgumentException {
      Iterator i$ = options.option(this.getPropertyOption()).getValues().iterator();

      while(i$.hasNext()) {
         String keyValueExpression = (String)i$.next();
         int pos = keyValueExpression.indexOf("=");
         if(pos < 0) {
            properties.put(keyValueExpression, "");
         } else {
            properties.put(keyValueExpression.substring(0, pos), keyValueExpression.substring(pos + 1));
         }
      }

      return properties;
   }
}
