package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTillDistanceCommand;
import com.team2502.robot2017.command.StayStraightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG2 extends CommandGroup
{
    public AutoCommandG2()
    {
    	addSequential(new StayStraightCommand(90, 5D));
    	addSequential(new StayStraightCommand(0, 5D));

    }
}
