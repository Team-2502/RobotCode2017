package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXMoveCommand extends Command{

	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	private DistanceSensorSubsystem Sensor;
	public double currentYaw;
	private boolean forever = false;
	public boolean done = false;;
	private long runTime;
	private long startTime;
	private double deadZone = 2;
	private double elapsedTime;
	private double speed;
	
	

	public NavXMoveCommand(){
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset();
        targetYaw = 0;
        forever = true;
		this.runTime = (long)  5000;
	}

    public NavXMoveCommand(double angle) 
    {
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset();
        targetYaw = angle;

		forever = true;

	}
    
    public NavXMoveCommand(double angle, double runTime)
    {
    	requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
    	
        navx.reset();
		targetYaw = angle;
		this.runTime = (long) runTime*1000;
    }


	@Override
	protected void initialize() 
	{
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() 
	{
		elapsedTime = System.currentTimeMillis() - startTime;
		speed = getSpeed(elapsedTime);
		currentYaw = Robot.NAVX.getYaw();
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
//		if (Sensor.getSensorDistance() > 14)
//		{ 

		if(Math.abs(currentYaw - targetYaw) > deadZone)
		{	
			// right = pos
			// left = neg
			if(currentYaw > targetYaw)
			{
				driveTrain.runMotors(-1*speed, -1 * speed);
			} 
			else if(currentYaw < targetYaw)
			{
				driveTrain.runMotors(speed, speed);

			}
		}
		else
		{	
			driveTrain.runMotors(0.5, -0.5);
		}
//		}
//		else
//		{
//			startTime = System.currentTimeMillis();
//		}
//		
	}
	
		
				
		
		

	@Override
	protected boolean isFinished() {
//		 Will end if time elapsed while at targetYaw or at appropriate distance
		if(forever)
		{
			return Math.abs(currentYaw - targetYaw) > deadZone;
		}
		else
		{
			if(Math.abs(currentYaw - targetYaw) > deadZone)
			{
				return System.currentTimeMillis() - startTime > runTime;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
	
	protected double getSpeed(double time) {
		if(targetYaw == 0){
			return 0.5;
		}
		else
		{
//			return Math.pow(Math.E, (-1 * time / 10000));
			return 1/(1+Math.pow(Math.E, time/2500));
		}
	}

}
