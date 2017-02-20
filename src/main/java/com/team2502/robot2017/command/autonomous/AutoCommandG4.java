package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTillDistanceCommand;
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelShootCommand;
import com.team2502.robot2017.command.NavXMoveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG4 extends CommandGroup
{
    public AutoCommandG4()
    {	//goesFromMiddle
    	
    	//goesToGearStationAndDropsOffGear
    	addSequential(new DriveTillDistanceCommand(14));
    	addSequential(new GearCommandG());
    	
    	//goes to boiler
    	addSequential(new DriveTimeCommand(((1/196.50)*114.3)/2));
    	addSequential(new NavXMoveCommand(90,1D));
    	addSequential(new DriveTimeCommand(((1/196.50)*171.45)/(3/4)));
    	addSequential(new NavXMoveCommand(-30,1D));
    	addSequential(new DriveTimeCommand(((1/196.50)*171.45)/(1/4)));
    	
    	//shoots
    	addSequential(new FlywheelShootCommand(7));
    	addParallel(new GearCommandG());
    	
    	
    }
}
