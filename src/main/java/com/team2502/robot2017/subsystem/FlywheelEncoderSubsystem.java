package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class FlywheelEncoderSubsystem extends Subsystem
{
    private final CANTalon flywheelTalon;
    private Encoder enc;
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }
    
    public FlywheelEncoderSubsystem() {
        // TODO Auto-generated constructor stub
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        
        // BEGIN ENCODER CODE
        // 0 and 1 are the port numbers for the 2 digital inputs
        // False tells the encoder to not invert counting direction
        // k4x means FPGA is used and 4x accuracy is obtained
        
        enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        
        // influences getRate method, fiddle with it if needed.
        enc.setMaxPeriod(1000);
        enc.setMinRate(1);
        enc.setDistancePerPulse(5);
        
        // influences counting direction, change if the counting is weird
        enc.setReverseDirection(false);
        
        // sets number of samples to average when determine the period
        // must be between 1 and 127
        enc.setSamplesToAverage(7);
        
            enc.get();
            enc.getRate();
            }
        // target value of rate should exist

	public double getSpeed() {
		// TODO Auto-generated method stub
		enc.getRaw();
		enc.getRate();
		
		return enc.get();
		
	}
        
       //flywheel_speed = .580
        
    }

    
