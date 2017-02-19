package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.command.NavXMoveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
    	addSequential(new NavXMoveCommand(0, 5D));
    	addSequential(new NavXMoveCommand(90));
        addSequential(new AutoVCommand(true));
        addSequential(new GearCommandG(true));
        addSequential(new GearCommandG(false));
        addSequential(new AutoVCommand(false));
        addSequential(new FlywheelCommand(1.2D));

    }
}
