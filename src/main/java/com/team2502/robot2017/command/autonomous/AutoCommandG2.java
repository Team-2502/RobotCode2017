package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG2 extends CommandGroup
{
    public AutoCommandG2()
    {

//    	addSequential(new DriveTimeCommand(3.0D));
//    	addSequential(new NavXMoveCommand(0.2));
    	addSequential(new NavXMoveCommand(-90));
//    	addSequential(new DriveTimeCommand(2.0D));

    	
    }
}