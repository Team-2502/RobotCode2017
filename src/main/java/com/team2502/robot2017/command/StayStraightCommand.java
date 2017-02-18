package com.team2502.robot2017.command;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StayStraightCommand extends Command{

	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	private DistanceSensorSubsystem Sensor;
	
	public StayStraightCommand(double angle) {
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset();
		targetYaw = angle;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() 
	{
		double currentYaw = Robot.NAVX.getYaw();
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
		if (Sensor.getSensorDistance() > 14)
		{
			if(Math.abs(currentYaw - targetYaw) > 2)
			{
				if(currentYaw > targetYaw)
				{
					driveTrain.runMotors(-1, -0.5);
				} 
				else if(currentYaw < targetYaw)
				{
					driveTrain.runMotors(0.5, 1);
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
