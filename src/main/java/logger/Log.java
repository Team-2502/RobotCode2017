package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Log
{
    private static boolean        m_debug = false;
    private static long           startTime;
    private static BufferedWriter bw = null;
    private static boolean        useLogFile = false;
    
    public static void close()
    {
        try { bw.close(); }
        catch(IOException e) {}
    }
    
    public static void createLogger() { createLogger(false); }
    
    public static void createLogger(String logDir) { createLogger(false, logDir); }
    
    public static void createLogger(boolean debug) { createLogger(debug, null); }
    
    public static void createLogger(boolean debug, String logDir)
    {
        startTime = System.currentTimeMillis();
        try
        {
            if(logDir != null && logDir.length() != 0)
            {
                File f = new File(logDir);
                f.mkdirs();
                f = new File(logDir + "/log_" + (startTime / 1000) + ".log");
                f.createNewFile();
                bw = new BufferedWriter(new FileWriter(f));
                useLogFile = true;
            }
        } catch(IOException e) { trace("Unable to create Log File."); }
        m_debug = debug;
        debug("Logger starting in debug mode.");
    }
    
    public static boolean isDebugMode() { return m_debug; }
    
    public static void setDebugMode(boolean debug)
    {
        if(m_debug && !debug) { Log.debug("Logger disabling debug mode."); }
        if(!m_debug && debug) { Log.debug("Logger enabling debug mode."); }
        m_debug = debug;
    }

    private static String getTime()
    {
        long secs = (System.currentTimeMillis() - startTime) / 1000;
        int mins = (int) (secs / 60);
        secs -= mins * 60;
        int hours = mins / 60;
        mins -= hours * 60;
        return "[" + String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs) + "]";
    }

    public static String getCallerClassName()
    {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++)
        {
            StackTraceElement ste = stElements[i];
            if(!ste.getClassName().equals(Log.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) { return ste.getClassName(); }
        }
        return null;
    }

    public static void log(LogType type, String msg)
    {
        String out = new StringBuilder(getTime()).append(" [").append(type).append("] [").append(getCallerClassName()).append("] ").append(msg).toString();
        try { if(useLogFile) { bw.write(out + "\n"); } }
        catch(IOException e) { useLogFile = false; }
        if (type == LogType.DEBUG && !m_debug) { return; }
        type.output.println(out);
        type.output.flush();
    }

    /* BEGIN_INFO */
    public static void info(String msg) { log(LogType.INFO, msg); }

    public static void info(int msg) { info(String.valueOf(msg)); }

    public static void info(long msg) { info(String.valueOf(msg)); }

    public static void info(short msg) { info(String.valueOf(msg)); }

    public static void info(byte msg) { info(String.valueOf(msg)); }

    public static void info(double msg) { info(String.valueOf(msg)); }

    public static void info(float msg) { info(String.valueOf(msg)); }

    public static void info(boolean msg) { info(String.valueOf(msg)); }

    public static void info(Object msg) { info(msg.toString()); }
    /* END_INFO */

    /* BEGIN_WARN */
    public static void warn(String msg) { log(LogType.WARN, msg); }

    public static void warn(int msg) { warn(String.valueOf(msg)); }

    public static void warn(long msg) { warn(String.valueOf(msg)); }

    public static void warn(short msg) { warn(String.valueOf(msg)); }

    public static void warn(byte msg) { warn(String.valueOf(msg)); }

    public static void warn(double msg) { warn(String.valueOf(msg)); }

    public static void warn(float msg) { warn(String.valueOf(msg)); }

    public static void warn(boolean msg) { warn(String.valueOf(msg)); }

    public static void warn(Object msg) { warn(msg.toString()); }
    /* END_WARN */

    /* BEGIN_ERROR */
    public static void error(String msg) { log(LogType.ERROR, msg); }

    public static void error(int msg) { error(String.valueOf(msg)); }

    public static void error(long msg) { error(String.valueOf(msg)); }

    public static void error(short msg) { error(String.valueOf(msg)); }

    public static void error(byte msg) { error(String.valueOf(msg)); }

    public static void error(double msg) { error(String.valueOf(msg)); }

    public static void error(float msg) { error(String.valueOf(msg)); }

    public static void error(boolean msg) { error(String.valueOf(msg)); }

    public static void error(Object msg) { error(msg.toString()); }
    /* END_ERROR */

    /* BEGIN_TRACE */
    public static void trace(String msg) { log(LogType.TRACE, msg); }

    public static void trace(int msg) { trace(String.valueOf(msg)); }

    public static void trace(long msg) { trace(String.valueOf(msg)); }

    public static void trace(short msg) { trace(String.valueOf(msg)); }

    public static void trace(byte msg) { trace(String.valueOf(msg)); }

    public static void trace(double msg) { trace(String.valueOf(msg)); }

    public static void trace(float msg) { trace(String.valueOf(msg)); }

    public static void trace(boolean msg) { trace(String.valueOf(msg)); }

    public static void trace(Object msg) { trace(msg.toString()); }
    
    public static void trace(StackTraceElement[] e)
    {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement el : e) { sb.append("\tat ").append(el.toString()).append("\n"); }
        System.err.println(sb.toString());
        System.err.flush();
    }
    
    public static void trace(Exception e, String msg)
    {
        if(msg != null && msg.length() != 0) { trace(msg); }
        trace(e.toString());
        trace(e.getStackTrace());
    }
    
    public static void trace(Exception e) { trace(e, null); }
    /* END_TRACE */

    /* BEGIN_DEBUG */
    public static void debug(String msg) { log(LogType.DEBUG, msg); }

    public static void debug(int msg) { debug(String.valueOf(msg)); }

    public static void debug(long msg) { debug(String.valueOf(msg)); }

    public static void debug(short msg) { debug(String.valueOf(msg)); }

    public static void debug(byte msg) { debug(String.valueOf(msg)); }

    public static void debug(double msg) { debug(String.valueOf(msg)); }

    public static void debug(float msg) { debug(String.valueOf(msg)); }

    public static void debug(boolean msg) { debug(String.valueOf(msg)); }

    public static void debug(Object msg) { debug(msg.toString()); }
    /* END_DEBUG */

    public enum LogType
    {
        INFO(System.out),
        WARN(System.out),
        ERROR(System.err),
        TRACE(System.err),
        DEBUG(System.out);
        
        public PrintStream output;
        private LogType(PrintStream output) { this.output = output; }
    }
}