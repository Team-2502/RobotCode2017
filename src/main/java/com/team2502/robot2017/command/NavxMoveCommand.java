package com.team2502.robot2017.command;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavxMoveCommand extends Command{

	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	private DistanceSensorSubsystem Sensor;

	public double currentYaw;
	public boolean continuous;
	private long runTime;
	private long startTime;
    public NavxMoveCommand(long runTime) 
    {

		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        

		this.runTime = (long) runTime;

	}
	
    public NavxMoveCommand(double runTime )
    {
        this((long) (runTime * 1000));
    }
    
    public NavxMoveCommand(double angle, double runTime)
    {
    	if(angle == 0)
        {
        	continuous = true;
        }
        else if(angle != 0)
        {
        	continuous = false;
        }
        navx.reset();
		targetYaw = angle;
		this.runTime = (long) runTime;
    }
 

	@Override
	protected void initialize() 
	{
		navx.reset();
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() 
	{

		currentYaw = Robot.NAVX.getYaw();
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
		if (Sensor.getSensorDistance() > 14)
		{
			if(Math.abs(currentYaw - targetYaw) > 10)
			{
				if(currentYaw > targetYaw)
				{
					driveTrain.runMotors(-0.1, -0.5);
				} 
				else if(currentYaw < targetYaw)
				{
					driveTrain.runMotors(0.5, 0.1);
				}
			}
			else
			{
				driveTrain.runMotors(-.5, .5);
			}
		}
		if (Math.abs(currentYaw-targetYaw) <= 10)
		{
			driveTrain.runMotors(0, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(!continuous){
			return (Math.abs(currentYaw - targetYaw) < 10);
		}
		else
		{
			return System.currentTimeMillis() - startTime > runTime;
		}
		
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
