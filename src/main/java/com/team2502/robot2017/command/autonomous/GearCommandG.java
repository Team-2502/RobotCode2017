package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.GearCommand;
import com.team2502.robot2017.command.LeverCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

@SuppressWarnings("WeakerAccess")
public class GearCommandG extends CommandGroup
{
    public GearCommandG(boolean on)
    {
        //addSequential(new LeverCommand(on));
        //addSequential(new GearCommand(on));
    }
}
