package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ClimberCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Add proper implementation
public class ClimberSubsystem extends Subsystem
{
    private CANTalon Climber;
    double neededTime = 115000;
    double Time = System.currentTimeMillis();

    public ClimberSubsystem()
    {
        Climber = new CANTalon(RobotMap.Motor.CLIMBER);
    }

    @Override
    protected void initDefaultCommand()
    {	
    	Time = System.currentTimeMillis();
        this.setDefaultCommand(new ClimberCommand());
    }

    public void activeDrive()
    {	
    	Time = System.currentTimeMillis();
    	if (Time == neededTime)
    	{
	        if(OI.JOYSTICK_FUNCTION.getRawButton(2)) 
	        { 
	        	Climber.set(1); 
	       	}
	        else if (OI.JOYSTICK_FUNCTION.getRawButton(7))
	        {
	        	Climber.set(-1);
	        }
	        else 
	        {
	        	Climber.set(0); 
	        }
    	}
    }

    public void stop()
    {
        Climber.set(0);
    }
}

