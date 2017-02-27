package com.team2502.robot2017.command;


import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;



public class MoveXDistance extends Command 
{
	public DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();

		
	double desiredDistance = 1;
	boolean stopped = false;
	double encDistanceLeft;
	double encDistanceRight;
	

	public MoveXDistance(double DesiredDistance)
	{
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
		desiredDistance = DesiredDistance;
		
	}

	@Override
	protected void initialize() 
	{	// TODO Auto-generated method stub
		driveTrain.resetLeft();
		driveTrain.resetRight();
		
	}

	@Override
	protected void execute() 
	{	// TODO Auto-generated method stubs
		encDistanceLeft = driveTrain.getPositionLeft();
		encDistanceRight = driveTrain.getPositionRight();
		
		if (encDistanceLeft < desiredDistance ) 
		{	
			driveTrain.runMotors(.5, 0);
		}
		if (encDistanceRight < desiredDistance)
		{
			driveTrain.runMotors(0, -.5);
		}
		
		else 
		{
			driveTrain.runMotors(0, 0);
			stopped = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() 
	{ 	// TODO Auto-generated method stub
		driveTrain.stop();
	}

	@Override
	protected void interrupted() 
	{	// TODO Auto-generated method stub
		end();
	}
	
}