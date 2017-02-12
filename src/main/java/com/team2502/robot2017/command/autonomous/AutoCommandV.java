package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class AutoCommandV extends Command
{
	public static DriveTrainSubsystem dt;
	public double offset;
	public double leftSpeed;
	public double rightSpeed;
    public boolean inFrontOfGear = false;
	public AutoCommandV() 
	{
		dt = new DriveTrainSubsystem();
		leftSpeed = 0.5;
		rightSpeed = -0.5;
	}
    	
    
	@Override
	protected void execute()
    {
		while(!inFrontOfGear){
    		offset = VisionSubsystem.getOffset();
    		if(!(offset == 1023) && (offset > 5 || offset < -5)){
    			offset = offset/100;
    			leftSpeed += offset;
    			rightSpeed += offset;
    			dt.runMotors(leftSpeed, rightSpeed);
    		}
			else if(offset > -5 || offset < 5)
    		{
    			leftSpeed = 0.5;
    			rightSpeed = -0.5;
    			dt.runMotors(leftSpeed, rightSpeed);
    			
    			if(Robot.DISTANCE_SENSOR.getSensorDistance() < 0.1)
    			{
    				inFrontOfGear = true;
    			}
    		}
    		// change in front of gear somewhere
    			
		}
    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return inFrontOfGear;
	}
	
	 protected void end()
	 {
		 dt.stop();
	 }
}



