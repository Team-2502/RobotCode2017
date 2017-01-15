package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.DashboardData;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommand extends Command
{
    @Override
    protected void initialize() {}

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() { return true; }

    @Override
    protected void end() {}

    @Override
    protected void interrupted() { end(); }

    private static AutonomousCommand instance;

    public static void autonomousInit() { instance = DashboardData.getAutonomous(); }

    public static void startS()
    {
        if(instance != null) { instance.start(); }
    }

    public static void cancelS()
    {
        if(instance != null) { instance.cancel(); }
    }
}
