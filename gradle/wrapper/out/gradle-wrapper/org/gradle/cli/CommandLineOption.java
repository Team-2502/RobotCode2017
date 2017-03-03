package org.gradle.cli;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommandLineOption {

   private final Set options = new HashSet();
   private Class argumentType;
   private String description;
   private String deprecationWarning;
   private boolean incubating;
   private final Set groupWith;


   public CommandLineOption(Iterable options) {
      this.argumentType = Void.TYPE;
      this.groupWith = new HashSet();
      Iterator i$ = options.iterator();

      while(i$.hasNext()) {
         String option = (String)i$.next();
         this.options.add(option);
      }

   }

   public Set getOptions() {
      return this.options;
   }

   public CommandLineOption hasArgument(Class argumentType) {
      this.argumentType = argumentType;
      return this;
   }

   public CommandLineOption hasArgument() {
      this.argumentType = String.class;
      return this;
   }

   public CommandLineOption hasArguments() {
      this.argumentType = List.class;
      return this;
   }

   public String getDescription() {
      StringBuilder result = new StringBuilder();
      if(this.description != null) {
         result.append(this.description);
      }

      if(this.deprecationWarning != null) {
         if(result.length() > 0) {
            result.append(' ');
         }

         result.append("[deprecated - ");
         result.append(this.deprecationWarning);
         result.append("]");
      }

      if(this.incubating) {
         if(result.length() > 0) {
            result.append(' ');
         }

         result.append("[incubating]");
      }

      return result.toString();
   }

   public CommandLineOption hasDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean getAllowsArguments() {
      return this.argumentType != Void.TYPE;
   }

   public boolean getAllowsMultipleArguments() {
      return this.argumentType == List.class;
   }

   public CommandLineOption deprecated(String deprecationWarning) {
      this.deprecationWarning = deprecationWarning;
      return this;
   }

   public CommandLineOption incubating() {
      this.incubating = true;
      return this;
   }

   public String getDeprecationWarning() {
      return this.deprecationWarning;
   }

   Set getGroupWith() {
      return this.groupWith;
   }

   void groupWith(Set options) {
      this.groupWith.addAll(options);
      this.groupWith.remove(this);
   }
}
