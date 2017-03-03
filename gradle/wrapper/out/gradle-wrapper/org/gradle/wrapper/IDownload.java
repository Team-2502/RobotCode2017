package org.gradle.wrapper;

import java.io.File;
import java.net.URI;

public interface IDownload {

   void download(URI var1, File var2) throws Exception;
}
