package org.gradle.wrapper;

import java.net.URI;

public class WrapperConfiguration {

   private URI distribution;
   private String distributionBase = "GRADLE_USER_HOME";
   private String distributionPath = "wrapper/dists";
   private String zipBase = "GRADLE_USER_HOME";
   private String zipPath = "wrapper/dists";


   public URI getDistribution() {
      return this.distribution;
   }

   public void setDistribution(URI distribution) {
      this.distribution = distribution;
   }

   public String getDistributionBase() {
      return this.distributionBase;
   }

   public void setDistributionBase(String distributionBase) {
      this.distributionBase = distributionBase;
   }

   public String getDistributionPath() {
      return this.distributionPath;
   }

   public void setDistributionPath(String distributionPath) {
      this.distributionPath = distributionPath;
   }

   public String getZipBase() {
      return this.zipBase;
   }

   public void setZipBase(String zipBase) {
      this.zipBase = zipBase;
   }

   public String getZipPath() {
      return this.zipPath;
   }

   public void setZipPath(String zipPath) {
      this.zipPath = zipPath;
   }
}
