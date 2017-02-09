package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainGearSwitchSubsystem;
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
    public static double TimeStraight = 5;

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
    	leftTalon0.setVoltageRampRate(6);
    	leftTalon1.setVoltageRampRate(6);
    	rightTalon0.setVoltageRampRate(6);
    	rightTalon1.setVoltageRampRate(6);
    	
    		
    }
    

    @Override
    public void execute()
    {  	
	//These are test functions all will get renamed and made better/move what we want. 
   	driveTrainSubsystem.drive( 1, -1,5.77 + 43902/9999900 );
   	Timer.delay(10D);
//   	DriveTrainGearSwitchSubsystem.setGear(true);
//   	Timer.delay(1D);
   	driveTrainSubsystem.drive( 1, -1, 5 + 4.7625/17.98);
   	Timer.delay(10D);
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
    
    public double getTimerStraight()
    {
    	return TimeStraight;
    }
}
