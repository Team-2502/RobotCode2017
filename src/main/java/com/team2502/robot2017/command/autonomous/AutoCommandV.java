package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class AutoCommandV extends CommandGroup
{
    public AutoCommandV()
    {
    	double offset;
    	double leftSpeed = 1;
    	double rightSpeed = 1;
    	while(true){
    		offset = VisionSubsystem.getOffset();
    		if(!!(offset == 1023)){
    			
    		}
    		
    		
    	}
        // addSequential(new DriveStraightCommand(1, 1));
//        addSequential(new DriveTimeCommand(1.2));
//        addParallel(new WaitCommand(1.0D));
    }
}

