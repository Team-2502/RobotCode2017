package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensorSubsystem extends Subsystem
{
	public double distScaleFactor = 6.5231750;
    private static final double INPUT_VOLTAGE = 5.0D;
    public AnalogInput distanceSensor;

    public DistanceSensorSubsystem()
    {
        distanceSensor = new AnalogInput(RobotMap.Electrical.DISTANCE_SENSOR);
    }

    @Override
    protected void initDefaultCommand()
    {
        /* NO-OP */
    }
    public Object getSensorDistance()
    {	if(distanceSensor.getVoltage()*distScaleFactor < 6)
    	{
//<<<<<<< HEAD
//    		return 0;
//=======
    		return "The target is out of bounds";
    	}
    	
    	else if(distanceSensor.getVoltage()*distScaleFactor > 18 )
    	{
//<<<<<<< HEAD
//    		return 20;
//=======
    		return "The target is out of bounds";

    	}
    	
    	else
    	{
        	return(distanceSensor.getVoltage()*distScaleFactor);
    	}
    }
}
