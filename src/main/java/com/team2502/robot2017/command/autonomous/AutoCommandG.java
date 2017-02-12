package com.team2502.robot2017.command.autonomous;

//import com.team2502.robot2017.command.DriveTimeCommand;
//import com.team2502.robot2017.command.StopDrivingCommand;
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.DriveTimeFlywheelCommand;
import com.team2502.robot2017.command.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import com.team2502.robot2017.command.autonomous.AutoCommandV;

public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {

        addSequential(new DriveTimeCommand(1D));
        addSequential(new DriveTimeFlywheelCommand(2D));
        addSequential(new TurnCommand(2D));        

        // addSequential(new DriveStraightCommand(1, 1));
        addSequential(new AutoCommandV());
        //addSequential(new GearDrop());

    }
}
