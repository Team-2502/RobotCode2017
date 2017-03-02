package com.team2502.robot2017.command.autonomous;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG2 extends CommandGroup
{
    public AutoCommandG2()
    {
    	addSequential(new EncDriveToDistanceCommand(5D));
    	addSequential(new GearCommandG(false));
    }
}