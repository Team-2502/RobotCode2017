package logger;

public class PrintFormat
{
    private String format;
    private int[] timeOrganizer;

    public PrintFormat(String format, String timeFormat)
    {
        this.format = format;
        this.timeOrganizer = new int[] {0, 0, 0, 0, 0, 0};
        for(String spl : timeFormat.split(":"))
        {
            if(spl.contains("u")) { timeOrganizer[0] = spl.length(); }
            else if(spl.contains("s")) { timeOrganizer[1] = spl.length(); }
            else if(spl.contains("m")) { timeOrganizer[2] = spl.length(); }
            else if(spl.contains("h")) { timeOrganizer[3] = spl.length(); }
            else if(spl.contains("d")) { timeOrganizer[4] = spl.length(); }
        }
    }

    protected String formatTime(long mill, long secs, long mins, long hour, long days)
    {
        StringBuilder out   = new StringBuilder();
        long[]        times = new long[] {days, hour, mins, secs, mill};
        for(int i = 0; i < timeOrganizer.length; ++i)
        {
            if(timeOrganizer[i] == 0)
            {
                continue;
            }

            out.append(String.format("%0" + timeOrganizer[i] + "d", times[i])).append(":");
        }
        String out0 = out.toString();
        out0 = out0.substring(0, out0.length() - 1);
        return out0;
    }

    protected String getTime()
    {
        long mill = System.currentTimeMillis() - Log.startTime;
        long secs = mill / 1000;
        return formatTime(mill, secs % 60, (secs / 60) % 60, (secs / 3600) % 24, (secs / 86400));
    }

    public String getPrintString(String type, String caller, String msg) { return format.replace("timestamp", getTime()).replace("type", type).replace("caller_class", caller).replace("msg", msg); }
}