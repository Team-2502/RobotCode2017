package com.team2502.robot2017.command.autonomous;

//import com.team2502.robot2017.command.DriveTimeCommand;
//import com.team2502.robot2017.command.StopDrivingCommand;
import com.team2502.robot2017.command.FlywheelCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

import com.team2502.robot2017.command.TurnCommand;
import com.team2502.robot2017.command.autonomous.AutoCommandV;

public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {
        addSequential(new AutoCommandV());
        addSequential(new GearCommandGON());
        addSequential(new GearCommandGOFF());
        addSequential(new TurnCommand(1D));
        addSequential(new AutoCommandV());
        addSequential(new FlywheelCommand(1.2D));
    }
}
