package com.team2502.robot2017.subsystems;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PressureSensorSubsytem extends Subsystem
{
    private AnalogInput pressureSensor;

    public PressureSensorSubsytem() { pressureSensor = new AnalogInput(RobotMap.Electrical.PRESSURE_SENSOR); }

    @Override
    protected void initDefaultCommand() {}

    private static final double INPUT_VOLTAGE = 5.0D;

    public double getPressure() { return (250.0D * (pressureSensor.getAverageVoltage() / INPUT_VOLTAGE)) - 25.0D; }
}
