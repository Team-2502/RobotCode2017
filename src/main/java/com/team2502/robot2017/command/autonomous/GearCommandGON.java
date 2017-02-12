package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearCommandGON extends CommandGroup
{
	public GearCommandGON()
	{
	addSequential(new leverOn());
	addSequential(new Push());
	}
}
