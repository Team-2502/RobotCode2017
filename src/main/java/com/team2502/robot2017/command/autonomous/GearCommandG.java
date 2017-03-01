package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.GearCommandTop;
import com.team2502.robot2017.command.GearLeverCommand;
import com.team2502.robot2017.command.GearPushGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

@SuppressWarnings("WeakerAccess")
public class GearCommandG extends CommandGroup
{
    public GearCommandG(boolean on)
    {
        addSequential(new GearLeverCommand());
        addSequential(new GearPushGearCommand());
    }
}
