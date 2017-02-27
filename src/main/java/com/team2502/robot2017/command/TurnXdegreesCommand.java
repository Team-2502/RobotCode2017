package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class TurnXdegreesCommand extends Command
{	
	private DriveTrainSubsystem driveTrain;	
	double TargetDegrees;
	private AHRS navx;
    double currentDegrees;
	
	public TurnXdegreesCommand(double  Xdegrees)
	{
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        Xdegrees = TargetDegrees; 

	}
	
	@Override
	protected void initialize() 
	{
		// TODO Auto-generated method stub
		currentDegrees = navx.getAngle();
	}

	@Override
	protected void execute() 
	{
		// TODO Auto-generated method stub
		currentDegrees = navx.getAngle();
		if (currentDegrees == TargetDegrees)
		{
			driveTrain.runMotors(0, 0);
		}
		else if (currentDegrees < TargetDegrees)
		{
			driveTrain.runMotors(0, currentDegrees);
		}
		else 
		{
			driveTrain.runMotors(currentDegrees, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
