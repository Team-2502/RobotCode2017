package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

import com.team2502.robot2017.command.TurnLeftCommand;
import com.team2502.robot2017.command.autonomous.AutoVCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
//IThinkThatThisCLassIsVeryStuipdAndIWantToKillIt
    	addSequential(new DriveTimeCommand(1D));
        addSequential(new TurnLeftCommand(1D));
        addSequential(new DriveTimeCommand(1D));
        addSequential(new AutoVCommand());
        addSequential(new GearCommandGON());
        addSequential(new GearCommandGOFF());
        addSequential(new AutoVCommand());
        addSequential(new FlywheelCommand(1.2D));
        
    }
}
