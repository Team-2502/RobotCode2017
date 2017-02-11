package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PressureSensorSubsystem extends Subsystem
{
    private static final double INPUT_VOLTAGE = 5.0D;
    private AnalogInput pressureSensor;

    public PressureSensorSubsystem()
    {
        pressureSensor = new AnalogInput(RobotMap.Electrical.PRESSURE_SENSOR);
    }

    @Override
    protected void initDefaultCommand()
    {
        /* NO-OP */
    }

    /**
     * Calculates the current pressure based on an algorithm.
     *
     * @return Current Pressure
     */
    public double getPressure()
    {
        return (250.0D * (pressureSensor.getAverageVoltage() / INPUT_VOLTAGE)) - 25.0D;
    }
}
