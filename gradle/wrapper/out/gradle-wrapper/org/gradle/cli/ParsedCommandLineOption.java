package org.gradle.cli;

import java.util.ArrayList;
import java.util.List;

public class ParsedCommandLineOption {

   private final List values = new ArrayList();


   public String getValue() {
      if(!this.hasValue()) {
         throw new IllegalStateException("Option does not have any value.");
      } else if(this.values.size() > 1) {
         throw new IllegalStateException("Option has multiple values.");
      } else {
         return (String)this.values.get(0);
      }
   }

   public List getValues() {
      return this.values;
   }

   public void addArgument(String argument) {
      this.values.add(argument);
   }

   public boolean hasValue() {
      return !this.values.isEmpty();
   }
}
