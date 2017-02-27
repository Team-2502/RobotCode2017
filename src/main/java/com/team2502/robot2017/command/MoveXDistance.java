package com.team2502.robot2017.command;


import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;



public class MoveXDistance extends Command 
{
	public DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();

	
	
	double desiredDistance = 1;
	boolean stopped;
	int rotations;
	

	public MoveXDistance(double DesiredDistance)
	{
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
		double encDistanceLeft = driveTrain.getPositionLeft();
		double encDistanceRight = driveTrain.getPositionRight();
		
		if (encDistanceLeft > desiredDistance && encDistanceRight > desiredDistance && !stopped) 
		{
			driveTrain.runMotors(0, 0);
			
		}
		else 
		{
			driveTrain.runMotors(0.5, -0.5);
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