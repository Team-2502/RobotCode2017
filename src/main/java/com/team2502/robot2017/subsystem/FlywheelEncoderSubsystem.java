package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

public class FlywheelEncoderSubsystem extends Subsystem
{
    //sets up the velocity variable that is later assigned the velocity that the encoder reads.
    private double velocity;
    
    private final CANTalon flywheelTalon;
    
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }
    
    public FlywheelEncoderSubsystem() {
        // TODO Auto-generated constructor stub
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
