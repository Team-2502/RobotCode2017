package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import logger.Log;

@SuppressWarnings("WeakerAccess")
public class EncDriveToDistanceCommand extends Command
{
	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx = Robot.NAVX;
	private DistanceSensorSubsystem Sensor;
	public double currentYaw;
	private boolean forever = false;
	public boolean done = false;;
	private long runTime;
	private long startTime;
	private double deadZone = 2;
	private double elapsedTime;
	private double speed;
	double targetDistance;
	double currentDistanceLeft;
	double currentDistanceRight;
	double currentDistance;
	double distanceToAngle = 360*(Math.PI * 4);

	    /**
	     * @param runTime Time to run for in milliseconds.
	     */
	public EncDriveToDistanceCommand(long runTime)
    {
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        this.runTime = runTime;
	}

	    /**
	     * @param runTime Time to run for in seconds.
	     */
	public EncDriveToDistanceCommand(double runTime)
    {
		this((long) (runTime * 1000));
	}

    @Override	
    protected void initialize()
    {
        driveTrain.setAutonSettings(driveTrain.leftTalon0);
        driveTrain.setAutonSettings(driveTrain.rightTalon1);
        targetDistance = 4.65;
        startTime = System.currentTimeMillis();
//    	navx.resetDisplacement();
    	
    }

    @Override
    protected void execute()
    {
//        elapsedTime = System.currentTimeMillis() - startTime;

    	SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
	   	currentDistanceLeft = driveTrain.getPostition(driveTrain.leftTalon0);
	   	currentDistanceRight = driveTrain.getPostition(driveTrain.rightTalon1);
	   	
	    driveTrain.leftTalon0.set(-targetDistance);
	    driveTrain.rightTalon1.set(targetDistance);
	    
	    currentDistance = (currentDistanceLeft + currentDistanceRight)/2;
	    
	    
			
	}
	
	protected double getSpeed(double time) 
	{
		if(targetYaw == 0)
		{
			return 0.5;
		}
		else
		{
//			return Math.pow(Math.E, (-1 * time / 10000));
			return 4000/((time * time) + 4000);
		}
	}

    @Override
    protected boolean isFinished()
    {
      if(targetDistance == currentDistance)
      {
    	  return true;
      }
      else
      {
    	 return System.currentTimeMillis() - startTime > runTime; 
      }
      
    }
    public double getEncYay()
    {
    	return currentYaw; 
    }

    @Override
    protected void end()
    {
        driveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}