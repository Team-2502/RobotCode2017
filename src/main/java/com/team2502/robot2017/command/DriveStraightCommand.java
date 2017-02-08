package com.team2502.robot2017.command;


import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;


public class DriveStraightCommand extends Command
{	
	private final DriveTrainSubsystem driveTrainSubsystem;
	

	
	
	
	public DriveStraightCommand()
	{
		requires(Robot.DRIVE_TRAIN);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
	}

	@Override
	protected void initialize() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() 
	{
		
		driveTrainSubsystem.drive(0.2,0.2,5);
    	
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() 
	{
		// TODO Auto-generated method stub
		driveTrainSubsystem.stop();
	}

	@Override
	protected void interrupted() 
	{
		// TODO Auto-generated method stub
		driveTrainSubsystem.stop();
	}

	public DriveTrainSubsystem getDriveTrainSubsystem() {
		return driveTrainSubsystem;
	}
	

}
