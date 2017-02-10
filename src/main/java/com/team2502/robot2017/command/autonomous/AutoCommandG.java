package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {
        // addSequential(new DriveStraight(1, 1));
        addSequential(new DriveTime(1.2));
        addParallel(new WaitCommand(1.0D));
    }
}
