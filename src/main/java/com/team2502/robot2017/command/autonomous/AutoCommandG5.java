package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveBackwardsCommand;
import com.team2502.robot2017.command.DriveTillDistanceCommand;
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelShootCommand;
import com.team2502.robot2017.command.NavXMoveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG5 extends CommandGroup
{
    public AutoCommandG5()
    {	//StartAtTheOppositeOf
    	
    	//dropsOfGear
    	addSequential(new DriveBackwardsCommand((1/196.50)*114.3));
    	addSequential(new NavXMoveCommand(60,2D));
    	addSequential(new DriveTillDistanceCommand(14));
    	addSequential(new GearCommandG());
    	
    	//LinesUp 
    	addSequential(new DriveTimeCommand((1/196.50)*78.5));
    	addSequential(new NavXMoveCommand(-130,2D));
    	
    	//movesAroundAirship
    	addSequential(new DriveTimeCommand((1/196.50)*284.8));
    	addParallel(new NavXMoveCommand(-180,(1/196.50)*284.8));
    	
    	//movesToBoiler
    	addSequential(new DriveTimeCommand((1/196.50)*58.875));
    	addSequential(new NavXMoveCommand(60,1D));
    	addSequential(new DriveTimeCommand((1/196.50)*58.875));
   
    	//shoots
    	addSequential(new FlywheelShootCommand(7));
    	addParallel(new GearCommandG());
   
    }
}
