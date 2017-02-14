package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTillDistanceCommand extends Command
{	
	private DistanceSensorSubsystem Sensor;
	private DriveTrainSubsystem driveTrain;
	double Distance;
	boolean Close = false;
	
	public DriveTillDistanceCommand (double Distance)
	{
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
	}
	@Override
	protected void initialize() 
	{	// TODO Auto-generated method stub
	
	}

	@Override
	protected void execute() 
	{	// TODO Auto-generated method stub
		if (!Close)
		{
			driveTrain.runMotors(1, 1);
		}
		if(Sensor.getSensorVoltage() == Distance)
		{
			Close = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Close;
	}

	@Override
	protected void end() 
	{	// TODO Auto-generated method stub
		 driveTrain.stop();
	}

	@Override
	protected void interrupted() 
	{ // TODO Auto-generated method stub
		end();
		
	}
	

}
