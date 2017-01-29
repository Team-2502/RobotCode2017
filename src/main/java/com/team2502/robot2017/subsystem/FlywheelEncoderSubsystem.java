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
    }

    // getSpeed() returns the current velocity of the flywheel.
    // This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
	public int getSpeed()
	{
		return flywheelTalon.getEncVelocity();
	} 
	
	public void flywheelDrive()
	{	
		// Determines if the flywheel is already active.
		// If active, turn off flywheel at button press.
		// Else, turn on flywheel at button press.
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
	
	public void stop() {}
}

    
