package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;


public class AutonomousCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;

    
    public int m = 1000;
    public int L = 1000;

    public AutonomousCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
   
        
    }

    @Override
    protected void initialize() {
    	AutonomousCommand.startS();
    }

    @Override
    protected void execute() {
    	driveTrainSubsystem.driveS();
    }

    @Override
    protected boolean isFinished() { return true; }

    @Override
    protected void end() 
    { 
    	driveTrainSubsystem.stop(); 
    }

    @Override
    protected void interrupted() 
    { 
    	driveTrainSubsystem.stop(); 
    }

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
