package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.subsystem.IsaacsSpecialSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class Pull extends Command
{
    private IsaacsSpecialSubsystem ISS;

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() 
	{
		// TODO Auto-generated method stub
		ISS.pullGear();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}