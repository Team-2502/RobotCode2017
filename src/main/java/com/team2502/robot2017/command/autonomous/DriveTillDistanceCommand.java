package com.team2502.robot2017.command.autonomous;

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
	double distanceR;
	
	public DriveTillDistanceCommand (double distance)
	{
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        distance = distanceR;
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() 
	{	// TODO Auto-generated method stub
			driveTrain.leftSpeed = -0.5;
			driveTrain.rightSpeed = 0.5;
			if (Sensor.getSensorDistance() > 14)
			{
				driveTrain.runMotors(driveTrain.leftSpeed, driveTrain.rightSpeed);
			}
			else 
			{
				driveTrain.runMotors(0, 0);
			}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
