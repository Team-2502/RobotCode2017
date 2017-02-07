package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.command.DriveStraightCommand;

public class AutonomousCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final VisionSubsystem visionSubsystem;
    
    public int m = 1000;
    public int L = 1000;

    public AutonomousCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        visionSubsystem = Robot.VISION;
        
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() 
    {  long millisecondsToRun0 = 1000;
    	long initTime = Utility.getFPGATime();
	   while (Utility.getFPGATime() - initTime <= millisecondsToRun0)
	{
    	driveTrainSubsystem.driveS();
    	m = m-1; 
    	
	} 
	if (m == 0) 
	{	long millisecondsToRun1 = 1000;
	long initTime0 = Utility.getFPGATime();
   while (Utility.getFPGATime() - initTime0 <= millisecondsToRun1)
   {
		driveTrainSubsystem.driveTL();
		L = L-1; 
   }
	} if (L==0)
	  driveTrainSubsystem.driveTR();
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
