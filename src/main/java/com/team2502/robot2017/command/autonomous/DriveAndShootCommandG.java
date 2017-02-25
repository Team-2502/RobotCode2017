package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.command.ShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndShootCommandG extends CommandGroup
{
    public DriveAndShootCommandG()
    {
        addSequential(new DriveTimeCommand(1.2));
        addSequential(new ShootCommand(1.2));
    }
}
