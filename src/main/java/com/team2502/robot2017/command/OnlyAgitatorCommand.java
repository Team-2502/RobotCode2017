package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class OnlyAgitatorCommand extends Command {

	private final ShooterSubsystem ShooterSubsystem;
	
	public OnlyAgitatorCommand() 
	{
		requires(Robot.SHOOTER);
        ShooterSubsystem = Robot.SHOOTER;
	}

	@Override
	protected void initialize() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() 
	{
        ShooterSubsystem.onlySpinAgitator();
	}

	@Override
	protected boolean isFinished() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() 
	{
        ShooterSubsystem.feederTalon2.set(0);
	}

	@Override
	protected void interrupted() 
	{
		end();
	}

}
