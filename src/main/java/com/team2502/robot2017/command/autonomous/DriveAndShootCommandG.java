package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.ShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndShootCommandG extends CommandGroup
{
    public DriveAndShootCommandG()
    {
        addSequential(new DriveTimeCommand(1.2));
        addSequential(new ShooterCommand(1.2));
    }
}
