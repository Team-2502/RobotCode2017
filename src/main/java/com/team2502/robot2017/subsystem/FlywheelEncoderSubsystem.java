package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.FlywheelCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

public class FlywheelEncoderSubsystem extends Subsystem
{
	
	public boolean isActive = false;
	
    private final CANTalon flywheelTalon;
    
    @Override
    protected void initDefaultCommand() 
    {
    	this.setDefaultCommand(new FlywheelCommand());
    }
    
    public FlywheelEncoderSubsystem()
    {
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        
        // BEGIN ENCODER CODE
        // 0 and 1 are the port numbers for the 2 digital inputs
        // False tells the encoder to not invert counting direction
        // k4x means FPGA is used and 4x accuracy is obtained
        
        // influences getRate method, fiddle with it if needed.  
        // influences counting direction, change if the counting is weird
        
        // sets number of samples to average when determine the period
        // must be between 1 and 127

    }
        // target value of rate should exist

	public int getSpeed()
	{
		return flywheelTalon.getEncVelocity();
	} 
	
	public void flywheelDrive()
	{	
		if(OI.JOYSTICK_DRIVE_LEFT.getTrigger()) 
		{
			if(isActive) 
			{
				flywheelTalon.set(0); 
				isActive = false; 
			}
			else 
			{
				flywheelTalon.set(0.5);
				isActive = true;
			}
		}
	}
	
	public void stop() 
	{
		
	}
}

    
