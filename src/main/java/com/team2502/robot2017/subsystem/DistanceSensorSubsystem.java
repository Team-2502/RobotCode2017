package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensorSubsystem extends Subsystem
{
    private static final double INPUT_VOLTAGE = 5.0D;
    private AnalogInput distanceSensor;

    public DistanceSensorSubsystem()
    {
        distanceSensor = new AnalogInput(RobotMap.Electrical.DISTANCE_SENSOR);
    }

    @Override
    protected void initDefaultCommand() {}

    public double getSensorDistance()
    {
        return distanceSensor.getVoltage();
    }
}