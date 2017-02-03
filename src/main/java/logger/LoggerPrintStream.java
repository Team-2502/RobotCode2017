package logger;

import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

@SuppressWarnings({ "WeakerAccess" })
public class LoggerPrintStream extends PrintStream
{
    protected final boolean isOutputStream;
    private transient int depth;

    public LoggerPrintStream(@NotNull("Passed a null value to parameter[0] at `logger.LoggerPrintStream(String)`") PrintStream original)
    {
        super(original);
        this.isOutputStream = original.equals(System.out);
        depth = 0;
    }

    public void outputln(@NotNull("Passed a null value to parameter[0] at `logger.LoggerPrintStream#outputln(java.lang.String)`") String s) { super.println(s); }


    @Override
    public void println(@NotNull("Passed a null value to parameter[0] at `logger.LoggerPrintStream#println(java.lang.String)`") String msg)
    {
        if(isOutputStream) { Log.log(Log.LogType.STD_OUT, msg, 1 + depth); }
        else { Log.log(Log.LogType.STD_ERR, msg, 1 + depth); }
        depth = 0;
    }

    @Override
    public void println(@NotNull("Passed a null value to parameter[0] at `logger.LoggerPrintStream#println(java.lang.Object)`") Object msg)
    {
        ++depth;
        println(msg.toString());
    }
}
