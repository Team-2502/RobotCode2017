package org.gradle.cli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.gradle.cli.CommandLineOption;
import org.gradle.cli.ParsedCommandLineOption;

public class ParsedCommandLine {

   private final Map optionsByString = new HashMap();
   private final Set presentOptions = new HashSet();
   private final List extraArguments = new ArrayList();


   ParsedCommandLine(Iterable options) {
      Iterator i$ = options.iterator();

      while(i$.hasNext()) {
         CommandLineOption option = (CommandLineOption)i$.next();
         ParsedCommandLineOption parsedOption = new ParsedCommandLineOption();
         Iterator i$1 = option.getOptions().iterator();

         while(i$1.hasNext()) {
            String optionStr = (String)i$1.next();
            this.optionsByString.put(optionStr, parsedOption);
         }
      }

   }

   public String toString() {
      return String.format("options: %s, extraArguments: %s", new Object[]{this.quoteAndJoin(this.presentOptions), this.quoteAndJoin(this.extraArguments)});
   }

   private String quoteAndJoin(Iterable strings) {
      StringBuilder output = new StringBuilder();
      boolean isFirst = true;

      for(Iterator i$ = strings.iterator(); i$.hasNext(); isFirst = false) {
         String string = (String)i$.next();
         if(!isFirst) {
            output.append(", ");
         }

         output.append("\'");
         output.append(string);
         output.append("\'");
      }

      return output.toString();
   }

   public boolean hasOption(String option) {
      this.option(option);
      return this.presentOptions.contains(option);
   }

   public boolean hasAnyOption(Collection logLevelOptions) {
      Iterator i$ = logLevelOptions.iterator();

      String option;
      do {
         if(!i$.hasNext()) {
            return false;
         }

         option = (String)i$.next();
      } while(!this.hasOption(option));

      return true;
   }

   public ParsedCommandLineOption option(String option) {
      ParsedCommandLineOption parsedOption = (ParsedCommandLineOption)this.optionsByString.get(option);
      if(parsedOption == null) {
         throw new IllegalArgumentException(String.format("Option \'%s\' not defined.", new Object[]{option}));
      } else {
         return parsedOption;
      }
   }

   public List getExtraArguments() {
      return this.extraArguments;
   }

   void addExtraValue(String value) {
      this.extraArguments.add(value);
   }

   ParsedCommandLineOption addOption(String optionStr, CommandLineOption option) {
      ParsedCommandLineOption parsedOption = (ParsedCommandLineOption)this.optionsByString.get(optionStr);
      this.presentOptions.addAll(option.getOptions());
      return parsedOption;
   }

   void removeOption(CommandLineOption option) {
      this.presentOptions.removeAll(option.getOptions());
   }
}
