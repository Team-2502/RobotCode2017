
package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearCommandGOFF extends CommandGroup
{
	public GearCommandGOFF()
	{
	addSequential(new leverOff());
	addSequential(new Pull());
	}
}
