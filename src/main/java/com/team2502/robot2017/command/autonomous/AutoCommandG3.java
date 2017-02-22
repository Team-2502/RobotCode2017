package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveBackwardsCommand;
import com.team2502.robot2017.command.DriveTillDistanceCommand;
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelShootCommand;
import com.team2502.robot2017.command.NavXMoveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG3 extends CommandGroup
{
    public AutoCommandG3()
    {	
    	  //goesToHopper 
          addSequential(new DriveTimeCommand(((1/196.50)*114.3)/2));
          addSequential(new NavXMoveCommand(-90, 3D));
          addSequential(new DriveTimeCommand((1/196.50)*78.5));
          
          //goesToGear
		  addSequential(new DriveBackwardsCommand((((1/196.50)*114.3)/2)/2));
		  addSequential(new NavXMoveCommand(30,1D));
		  addSequential(new DriveBackwardsCommand((((1/196.5)*114.3)/4)/2));
		  addSequential(new NavXMoveCommand(-30,1D));
		  addSequential(new DriveTillDistanceCommand(14));
		  
		  //dropsOffgear
		  addSequential(new GearCommandG());
		  
		  //goesToBoiler
		  addSequential(new DriveTimeCommand(((1/196.50)*114.3)/2));
		  
		  //shootsBalls
		  addSequential(new FlywheelShootCommand(7)); 
		  addParallel(new GearCommandG());
		  
          
          
          
    }
    
}