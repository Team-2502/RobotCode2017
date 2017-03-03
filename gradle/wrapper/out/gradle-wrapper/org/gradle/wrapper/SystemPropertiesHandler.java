package org.gradle.wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemPropertiesHandler {

   public static Map getSystemProperties(File propertiesFile) {
      HashMap propertyMap = new HashMap();
      if(!propertiesFile.isFile()) {
         return propertyMap;
      } else {
         Properties properties = new Properties();

         try {
            FileInputStream pattern = new FileInputStream(propertiesFile);

            try {
               properties.load(pattern);
            } finally {
               pattern.close();
            }
         } catch (IOException var11) {
            throw new RuntimeException("Error when loading properties file=" + propertiesFile, var11);
         }

         Pattern pattern1 = Pattern.compile("systemProp\\.(.*)");
         Iterator i$ = properties.keySet().iterator();

         while(i$.hasNext()) {
            Object argument = i$.next();
            Matcher matcher = pattern1.matcher(argument.toString());
            if(matcher.find()) {
               String key = matcher.group(1);
               if(key.length() > 0) {
                  propertyMap.put(key, properties.get(argument).toString());
               }
            }
         }

         return propertyMap;
      }
   }
}
