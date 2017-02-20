package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.command.NavXMoveCommand;
import com.team2502.robot2017.command.TurnLeftCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        addSequential(new DriveTimeCommand(1.0D));
        addSequential(new NavXMoveCommand(-90, 3D));
        addSequential(new DriveTimeCommand(1.0D));
//        addSequential(new AutoVCommand(true));
        addSequential(new GearCommandG());
        addSequential(new GearCommandG());
//        addSequential(new AutoVCommand(false));
//        addSequential(new FlywheelCommand(1.2D));

    }
}
