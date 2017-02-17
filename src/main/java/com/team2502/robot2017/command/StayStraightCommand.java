package com.team2502.robot2017.command;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class StayStraightCommand extends Command{

	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	private DistanceSensorSubsystem Sensor;
	
	public StayStraightCommand() {
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
		targetYaw = navx.getYaw();
	}

	@Override
	protected void initialize() {targetYaw = navx.getYaw();}

	@Override
	protected void execute() 
	{
		double currentYaw = Robot.NAVX.getYaw();
	
		if (Sensor.getSensorDistance() > 14)
		{
			if(Math.abs(currentYaw - targetYaw) > 5)
			{
				if(currentYaw > targetYaw)
				{
					driveTrain.runMotors(driveTrain.leftSpeed*(currentYaw-targetYaw), driveTrain.rightSpeed*0);
				} 
				else if(currentYaw < targetYaw)
				{
					driveTrain.runMotors(driveTrain.leftSpeed*0, driveTrain.rightSpeed*(targetYaw-currentYaw));
				}
			}
			else
			{
				driveTrain.runMotors(-.5, .5);
			}
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
	protected void end() {}

	@Override
	protected void interrupted() {}

}
