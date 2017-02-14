package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;




public class DistanceSensorSubsystem extends Subsystem
{
    private AnalogInput distanceSensor;
    double scaleFactor = 6.49;
    
    

    public DistanceSensorSubsystem() { distanceSensor = new AnalogInput(RobotMap.Electrical.DISTANCE_SENSOR); }


    protected void initDefaultCommand() {}

    private static final double INPUT_VOLTAGE = 5.0D;

    public double getSensorVoltage() { return distanceSensor.getVoltage(); }
    
    public double getSensorDist() { 
    	double voltage = distanceSensor.getVoltage();
    	return voltage * scaleFactor;
    }
    
}
   
   
	