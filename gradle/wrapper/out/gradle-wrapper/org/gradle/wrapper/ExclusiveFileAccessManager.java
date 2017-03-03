package org.gradle.wrapper;

import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Callable;

public class ExclusiveFileAccessManager {

   public static final String LOCK_FILE_SUFFIX = ".lck";
   private final int timeoutMs;
   private final int pollIntervalMs;


   public ExclusiveFileAccessManager(int timeoutMs, int pollIntervalMs) {
      this.timeoutMs = timeoutMs;
      this.pollIntervalMs = pollIntervalMs;
   }

   public Object access(File exclusiveFile, Callable task) throws Exception {
      File lockFile = new File(exclusiveFile.getParentFile(), exclusiveFile.getName() + ".lck");
      lockFile.getParentFile().mkdirs();
      RandomAccessFile randomAccessFile = null;
      FileChannel channel = null;

      Object var9;
      try {
         long startAt = System.currentTimeMillis();
         FileLock lock = null;

         while(lock == null && System.currentTimeMillis() < startAt + (long)this.timeoutMs) {
            randomAccessFile = new RandomAccessFile(lockFile, "rw");
            channel = randomAccessFile.getChannel();
            lock = channel.tryLock();
            if(lock == null) {
               maybeCloseQuietly(channel);
               maybeCloseQuietly(randomAccessFile);
               Thread.sleep((long)this.pollIntervalMs);
            }
         }

         if(lock == null) {
            throw new RuntimeException("Timeout of " + this.timeoutMs + " reached waiting for exclusive access to file: " + exclusiveFile.getAbsolutePath());
         }

         try {
            var9 = task.call();
         } finally {
            lock.release();
            maybeCloseQuietly(channel);
            channel = null;
            maybeCloseQuietly(randomAccessFile);
            randomAccessFile = null;
         }
      } finally {
         maybeCloseQuietly(channel);
         maybeCloseQuietly(randomAccessFile);
      }

      return var9;
   }

   private static void maybeCloseQuietly(Closeable closeable) {
      if(closeable != null) {
         try {
            closeable.close();
         } catch (Exception var2) {
            ;
         }
      }

   }
}
