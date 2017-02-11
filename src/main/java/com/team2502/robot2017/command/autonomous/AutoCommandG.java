package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {
        // addSequential(new DriveStraightCommand(1, 1));
        addSequential(new DriveTimeCommand(1.2));
        addParallel(new WaitCommand(1.0D));
    }
}