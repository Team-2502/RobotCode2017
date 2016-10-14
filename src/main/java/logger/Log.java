package logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

@SuppressWarnings({ "WeakerAccess", "unused", "EmptyCatchBlock" })
public class Log
{
    private static boolean m_debug;
    private static boolean useLogFile;

    @Nullable
    protected static BufferedWriter bw = null;

    @Nullable
    protected static PrintFormat pf = null;

    public static boolean isDebugMode() { return m_debug; }

    public static void setDebugMode(boolean debug)
    {
        boolean tmp = m_debug;
        m_debug = debug;
        if(tmp && !debug) { Log.debug("Logger disabling debug mode."); } else if(!tmp && debug)
        {
            Log.debug("Logger enabling debug mode.");
        }
    }

    public static boolean usingLogFile() { return useLogFile; }

    public static void createLogger() { createLogger(false, ""); }

    public static void createLogger(@NotNull("Passed a null value to parameter[0] at `logger.Log#createLogger(java.lang.String)`") String logDir) { createLogger(false, logDir); }

    public static void createLogger(boolean debug) { createLogger(debug, ""); }

    public static void createLogger(boolean debug, @NotNull("Passed a null value to parameter[1] at `logger.Log#createLogger(boolean, java.lang.String)`") String logDir) { createLogger(debug, logDir, "[timestamp] [type] [caller_class]: msg", "hh:mm:ss"); }

    public static void createLogger(boolean debug,
                                    @NotNull("Passed a null value to parameter[1] at `logger.Log#createLogger(boolean, java.lang.String, java.lang.String, java.lang.String)`") String logDir,
                                    @NotNull("Passed a null value to parameter[2] at `logger.Log#createLogger(boolean, java.lang.String, java.lang.String, java.lang.String)`") String format,
                                    @NotNull("Passed a null value to parameter[3] at `logger.Log#createLogger(boolean, java.lang.String, java.lang.String, java.lang.String)`") String timeFormat)
    {
        pf = new PrintFormat(format, timeFormat);
        try
        {
            if(logDir.length() != 0)
            {
                File f = new File(logDir);
                //noinspection ResultOfMethodCallIgnored
                f.mkdirs();
                //noinspection StringBufferReplaceableByString
                f = new File(new StringBuilder(logDir).append(logDir.endsWith("/") ? "" : "/").append("log_").append(System.currentTimeMillis() / 1000).append(".log").toString());
                if(!f.createNewFile()) { throw new IOException(); }
                bw = new BufferedWriter(new FileWriter(f));
                useLogFile = true;
            }
        } catch(IOException e) { warn("Unable to create Log File."); }
        setDebugMode(debug || System.getProperty("debug") != null);
    }

    protected static void close()
    {
        if(bw == null) { return; }
        try { bw.close(); } catch(IOException e) {}
    }

    protected static void log(@NotNull("Passed a null value to parameter[0] at `logger.Log#log(logger.Log$LogType, java.lang.String)") LogType type,
                              @NotNull("Passed a null value to parameter[1] at `logger.Log#log(logger.Log.LogType, java.lang.String)") String msg, int depth)
    {
        if(pf == null) { createLogger(); }
        String out = pf.getPrintString(type.toString(), ClassGetter.getCallerClassName(depth), msg);
        try { if(useLogFile && bw != null) { bw.write(out + "\n"); } } catch(IOException e) { useLogFile = false; }
        if(type == LogType.DEBUG && !m_debug) { return; }
        if(type.output instanceof LoggerPrintStream) { ((LoggerPrintStream) type.output).outputln(out); } else
        {
            type.output.println(out);
        }
        type.output.flush();
    }

    protected static void log(@NotNull("Passed a null value to parameter[0] at `logger.Log#log(logger.Log$LogType, java.lang.String)") LogType type,
                              @NotNull("Passed a null value to parameter[1] at `logger.Log#log(logger.Log.LogType, java.lang.String)") String msg)
    { log(type, msg, 0); }

    /* BEGIN: STD_OUT */
    static void out(@NotNull("Passed a null value to parameter[0] at `logger.Log#out(String)`") String msg) { log(LogType.STD_OUT, msg); }

    static void out(@NotNull("Passed a null value to parameter[0] at `logger.Log#out(Object)`") Object msg) { out(msg.toString()); }
    /* END: STD_OUT */

    /* BEGIN: STD_ERR */
    static void err(@NotNull("Passed a null value to parameter[0] at `logger.Log#err(java.lang.String)`") String msg) { log(LogType.STD_ERR, msg); }

    static void err(@NotNull("Passed a null value to parameter[0] at `logger.Log#err(Object)`") Object msg) { err(msg.toString()); }
    /* END: STD_ERR */

    /* BEGIN: INFO */
    public static void info(@NotNull("Passed a null value to parameter[0] at `logger.Log#info(String)`") String msg) { log(LogType.INFO, msg); }

    public static void info(int msg) { info(String.valueOf(msg)); }

    public static void info(long msg) { info(String.valueOf(msg)); }

    public static void info(short msg) { info(String.valueOf(msg)); }

    public static void info(byte msg) { info(String.valueOf(msg)); }

    public static void info(double msg) { info(String.valueOf(msg)); }

    public static void info(float msg) { info(String.valueOf(msg)); }

    public static void info(boolean msg) { info(String.valueOf(msg)); }

    public static void info(@NotNull("Passed a null value to parameter[0] at `logger.Log#info(java.lang.Object)`") Object msg) { info(msg.toString()); }
    /* END: INFO */

    /* BEGIN: WARN */
    public static void warn(@NotNull("Passed a null value to parameter[0] at `logger.Log#warn(java.lang.String)`") String msg) { log(LogType.WARN, msg); }

    public static void warn(int msg) { warn(String.valueOf(msg)); }

    public static void warn(long msg) { warn(String.valueOf(msg)); }

    public static void warn(short msg) { warn(String.valueOf(msg)); }

    public static void warn(byte msg) { warn(String.valueOf(msg)); }

    public static void warn(double msg) { warn(String.valueOf(msg)); }

    public static void warn(float msg) { warn(String.valueOf(msg)); }

    public static void warn(boolean msg) { warn(String.valueOf(msg)); }

    public static void warn(@NotNull("Passed a null value to parameter[0] at `logger.Log#warn(java.lang.Object)`") Object msg) { warn(msg.toString()); }
    /* END: WARN */

    /* BEGIN: ERROR */
    public static void error(@NotNull("Passed a null value to parameter[0] at `logger.Log#error(java.lang.String)`") String msg) { log(LogType.ERROR, msg); }

    public static void error(int msg) { error(String.valueOf(msg)); }

    public static void error(long msg) { error(String.valueOf(msg)); }

    public static void error(short msg) { error(String.valueOf(msg)); }

    public static void error(byte msg) { error(String.valueOf(msg)); }

    public static void error(double msg) { error(String.valueOf(msg)); }

    public static void error(float msg) { error(String.valueOf(msg)); }

    public static void error(boolean msg) { error(String.valueOf(msg)); }

    public static void error(@NotNull("Passed a null value to parameter[0] at `logger.Log#error(java.lang.Object)`") Object msg) { error(msg.toString()); }
    /* END: ERROR */

    /* BEGIN: TRACE */
    public static void trace(@NotNull("Passed a null value to parameter[0] at `logger.Log#trace(java.lang.String)`") String msg) { log(LogType.TRACE, msg); }

    public static void trace(int msg) { trace(String.valueOf(msg)); }

    public static void trace(long msg) { trace(String.valueOf(msg)); }

    public static void trace(short msg) { trace(String.valueOf(msg)); }

    public static void trace(byte msg) { trace(String.valueOf(msg)); }

    public static void trace(double msg) { trace(String.valueOf(msg)); }

    public static void trace(float msg) { trace(String.valueOf(msg)); }

    public static void trace(boolean msg) { trace(String.valueOf(msg)); }

    public static void trace(@NotNull("Passed a null value to parameter[0] at `logger.Log#trace(java.lang.Object)`") Object msg) { trace(msg.toString()); }

    public static void trace(@NotNull StackTraceElement[] e)
    {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement el : e) { sb.append("\tat ").append(el.toString()).append("\n"); }
        trace(sb.toString());
    }

    public static void trace(@NotNull("Passed a null value to parameter[0] at `logger.Log#trace(java.lang.Exception, java.lang.String)`") Exception e, @NotNull("Passed a null value to parameter[1] at `logger.Log#trace(java.lang.Exception, java.lang.String)`") String msg)
    {
        if(msg.length() != 0) { trace(msg); }
        trace(e.toString());
        trace(e.getStackTrace());
    }

    public static void trace(@NotNull("Passed a null value to parameter[0] at `logger.Log#trace(java.lang.Exception)`") Exception e) { trace(e, ""); }
    /* END: TRACE */

    /* BEGIN: DEBUG */
    public static void debug(@NotNull("Passed a null value to parameter[0] at `logger.Log#debug(java.lang.String)`") String msg) { log(LogType.DEBUG, msg); }

    public static void debug(int msg) { debug(String.valueOf(msg)); }

    public static void debug(long msg) { debug(String.valueOf(msg)); }

    public static void debug(short msg) { debug(String.valueOf(msg)); }

    public static void debug(byte msg) { debug(String.valueOf(msg)); }

    public static void debug(double msg) { debug(String.valueOf(msg)); }

    public static void debug(float msg) { debug(String.valueOf(msg)); }

    public static void debug(boolean msg) { debug(String.valueOf(msg)); }

    public static void debug(@NotNull("Passed a null value to parameter[0] at `logger.Log#debug(java.lang.Object)`") Object msg) { debug(msg.toString()); }
    /* END: DEBUG */

    public enum LogType
    {
        STD_OUT(System.out),
        STD_ERR(System.err),
        INFO(System.out),
        WARN(System.out),
        ERROR(System.err),
        TRACE(System.err),
        DEBUG(System.out);

        @NotNull
        public final PrintStream output;

        LogType(@NotNull("Created a LogType without specifying a `java.io.PrintStream`") PrintStream output) { this.output = output; }
    }

    @SuppressWarnings("WeakerAccess")
    public static final class ClassGetter
    {
        private static final int BASE_DEPTH = 4;

        @NotNull("Error getting the stacktrace at `logger.Log$ClassGetter#getCallerClassName(int)")
        public static String getCallerClassName(int depth)
        {
            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stElements[BASE_DEPTH + depth];
            if(element.getClassName().startsWith("kotlin.io."))
            {
                element = stElements[BASE_DEPTH + 2 + depth];
            } else if(element.getClassName().startsWith("java.lang.Throwable"))
            {
                element = stElements[BASE_DEPTH + 4 + depth];
            }

            return element.getClassName();
        }

        @NotNull("Error getting the stacktrace at `logger.Log$ClassGetter#getCallerClassName()")
        public static String getCallerClassName() { return getCallerClassName(0); }
    }

    static
    {
        m_debug = false;
        useLogFile = false;
        bw = null;
        pf = null;
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run() { close(); }
        });
        System.setOut(new LoggerPrintStream(System.out));
        System.setErr(new LoggerPrintStream(System.err));
    }
}
