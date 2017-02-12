package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class AutoCommandV extends Command
{
	public static DriveTrainSubsystem driveTrainSubsystem;
	public double offset;
	public double leftSpeed;
	public double rightSpeed;
    
	public AutoCommandV() 
	{
		driveTrainSubsystem = new DriveTrainSubsystem();
		leftSpeed = 0.5;
		rightSpeed = -0.5;
	}
    	
    
	@Override
	protected void execute()
    {
		while(true){
    		offset = VisionSubsystem.getOffset();
    		if(!(offset == 1023) && (offset > 5 && offset < -5)){
    			offset = offset/100;
    			leftSpeed += offset;
    			rightSpeed += offset;
    			driveTrainSubsystem.runMotors(leftSpeed, rightSpeed);
    		}
			else if(offset == 0)
    		{
    			leftSpeed = 0.5;
    			rightSpeed = -0.5;
    			driveTrainSubsystem.runMotors(leftSpeed, rightSpeed);
    		}
    			
		}
    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}



