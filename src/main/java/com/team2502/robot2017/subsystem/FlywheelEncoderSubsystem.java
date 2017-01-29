package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.CanTalonJNI;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class FlywheelEncoderSubsystem extends Subsystem
{
    private final CANTalon flywheelTalon;
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
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
//		enc.getRaw();
//		enc.getRate();
		return flywheelTalon.getEncVelocity();
	}
        
       //flywheel_speed = .580
        
    }

    
