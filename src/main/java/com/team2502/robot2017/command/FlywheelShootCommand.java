package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FlywheelShootCommand extends CommandGroup
{
	public FlywheelShootCommand(double runtime)
	{
		addSequential(new ShootCommand(runtime));
		addParallel(new FlywheelFeederCommand(runtime));
	
	}
}
