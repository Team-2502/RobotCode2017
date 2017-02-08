package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class AutonomousCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final CANTalon leftTalon0;
    private final CANTalon leftTalon1;
    private final CANTalon rightTalon0;
    private final CANTalon rightTalon1;
    
    public int m = 1000;
    public int L = 1000;

    public AutonomousCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        
        leftTalon0 = new CANTalon(RobotMap.Motor.LEFT_TALON_0);
        leftTalon1 = new CANTalon(RobotMap.Motor.LEFT_TALON_1);
        rightTalon0 = new CANTalon(RobotMap.Motor.RIGHT_TALON_0);
        rightTalon1 = new CANTalon(RobotMap.Motor.RIGHT_TALON_1);
   
    }

    @Override
    public void initialize() {
    	
    	//AutonomousCommand.startS();

    }

    @Override
    public void execute()
    {  
   	 	
   	 	driveTrainSubsystem.drive( 1, -1, 5);
   	 	Timer.delay(5D);
   	 	driveTrainSubsystem.drive(1, 0, 5);
   	 	Timer.delay(5D);
   	 	driveTrainSubsystem.drive(0, -1, 5);
   	 	driveTrainSubsystem.stopDriveS();
   	 	AutonomousCommand.cancelS();
   	 	
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end() 
    { 
    	leftTalon0.set(0);
      	leftTalon1.set(0);
      	rightTalon0.set(0);
      	rightTalon1.set(0);
    	driveTrainSubsystem.stop(); 
    }

    @Override
    public void interrupted() 
    { 
    	driveTrainSubsystem.stop(); 
    }

    private static AutonomousCommand instance;

    public static void autonomousInit() { instance = DashboardData.getAutonomous(); }

    public static void startS()
    { 
//        if(instance != null) { instance.start(); }
        Scheduler.getInstance().add(instance);
    }

    public static void cancelS()
    {
        if(instance != null) { instance.cancel(); }
    }
    
    public static AutonomousCommand getInstance()
    {
    	return instance;
    }
}
