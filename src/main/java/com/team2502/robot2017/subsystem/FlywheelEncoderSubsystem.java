package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class FlywheelEncoderSubsystem extends Subsystem
{
    //sets up the velocity variable that is later assigned the velocity that the encoder reads.
    private double velocity;
    
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
        // enc.reset();
        enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        
        // influences getRate method, fiddle with it if needed
        enc.setMaxPeriod(.1);
        enc.setMinRate(10);
        enc.setDistancePerPulse(5);
        
        // influences counting direction, change if the counting is weird
        enc.setReverseDirection(false);
        
        // sets number of samples to average when determine the period
        // must be between 1 and 127
        enc.setSamplesToAverage(7);
        
        // getting encoder values
        // count is the count, reset when .reset is called
        int count = enc.get();
        double rate = enc.getRate();
        
        // target value of rate should exist
        
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
