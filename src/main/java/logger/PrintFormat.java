package logger;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({ "WeakerAccess", "StringBufferReplaceableByString" })
public class PrintFormat
{
    @NotNull
    private String format;
    @NotNull
    private int[] timeOrganizer;
    @NotNull
    private Timer timer;

    public PrintFormat(@NotNull String format, @NotNull String timeFormat)
    {
        this.format = format;
        this.timeOrganizer = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        for(String spl : timeFormat.split(":"))
        {
            if(spl.contains("n")) { timeOrganizer[0] = spl.length(); } else if(spl.contains("q"))
            {
                timeOrganizer[1] = spl.length();
            } else if(spl.contains("M")) { timeOrganizer[2] = spl.length(); } else if(spl.contains("s"))
            {
                timeOrganizer[3] = spl.length();
            } else if(spl.contains("m")) { timeOrganizer[4] = spl.length(); } else if(spl.contains("h"))
            {
                timeOrganizer[5] = spl.length();
            } else if(spl.contains("d")) { timeOrganizer[6] = spl.length(); }
        }
        timer = new Timer(timeOrganizer[0] > 0 || timeOrganizer[1] > 0);
    }

    @NotNull
    public String getPrintString(@NotNull String type, @NotNull String caller, @NotNull String msg) { return format.replace("timestamp", timer.getTime()).replace("type", type).replace("caller_class", caller).replace("msg", msg); }

    protected class Timer
    {
        @NotNull
        protected long[] times;

        @NotNull
        protected long[] lastTimes;

        @NotNull
        protected String[] stringTimes;

        protected final boolean nanoTime;
        protected final long startTime;

        protected Timer(boolean nanoTime)
        {
            times = new long[7];
            lastTimes = new long[] { -1, -1, -1, -1, -1, -1, -1 };
            stringTimes = new String[7];
            this.nanoTime = nanoTime;
            if(nanoTime) { startTime = System.nanoTime(); } else { startTime = System.currentTimeMillis(); }
        }

        @NotNull
        protected String formatTime()
        {
            StringBuilder out = new StringBuilder();
            for(int i = 0; i < timeOrganizer.length; ++i)
            {
                if(timeOrganizer[i] == 0) { continue; }
                if(times[i] == lastTimes[i]) { out.append(stringTimes[i]); } else
                {
                    out.append(String.format(new StringBuilder("%0").append(timeOrganizer[i]).append("d").toString(), times[i]));
                }
                out.append(":");
            }
            String out0 = out.toString();
            out0 = out0.substring(0, out0.length() - 1);
            return out0;
        }

        @NotNull
        protected String getTime()
        {
            if(nanoTime)
            {
                times[0] = System.nanoTime() - startTime;
                times[1] = times[0] % 1000;
            } else { times[2] = System.currentTimeMillis() - startTime; }
            long thousand = times[2] / 1000;
            times[2] %= 1000;
            times[3] = thousand % 60;
            times[4] = (thousand / 60) % 60;
            times[5] = (thousand / 3600) % 24;
            times[6] = thousand / 86400;
            return formatTime();
        }
    }
}
