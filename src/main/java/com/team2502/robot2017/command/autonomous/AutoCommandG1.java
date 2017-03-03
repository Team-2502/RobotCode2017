package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.ShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        addSequential(new DriveTimeCommand(1.0D));
        addSequential(new TurnLeftCommand(1.0D));
        addSequential(new DriveTimeCommand(1.0D));
        addSequential(new AutoVCommand(true));
        addSequential(new GearCommandG());
        addSequential(new GearCommandG());
        addSequential(new AutoVCommand(false));
        addSequential(new ShooterCommand(1.2D));

    }
}
