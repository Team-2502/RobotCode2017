package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensorSubsystem extends Subsystem
{
	private double distScaleFactor = 6.5231750;
    private static final double INPUT_VOLTAGE = 5.0D;
    private AnalogInput distanceSensor;

    public DistanceSensorSubsystem()
    {
        distanceSensor = new AnalogInput(RobotMap.Electrical.DISTANCE_SENSOR);
    }

    @Override
    protected void initDefaultCommand()
    {
        /* NO-OP */
    }

   
    public double getSensorDistance()
    {	if(distanceSensor.getVoltage()*distScaleFactor < 6)
    	{
    		return 20;
    	}
    	
    	else if(distanceSensor.getVoltage()*distScaleFactor > 18 )
    	{
    		return 0;
    	}
    	
    	else
    	{
        	return(distanceSensor.getVoltage()*distScaleFactor);
    	}
    }
}
