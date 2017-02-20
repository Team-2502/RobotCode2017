package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.GearCommandLever;
import com.team2502.robot2017.command.GearCommandPushGear;
import edu.wpi.first.wpilibj.command.CommandGroup;

@SuppressWarnings("WeakerAccess")
public class GearCommandG extends CommandGroup
{
    public GearCommandG()
    {
        addSequential(new GearCommandLever());
        addSequential(new GearCommandPushGear());
    }
}
