package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {
        addSequential(new DriveStraight(1, 1));
        addParallel(new WaitCommand(1.0D));
        Scheduler.getInstance().add(new WaitCommand(10.0D));

    }

}
