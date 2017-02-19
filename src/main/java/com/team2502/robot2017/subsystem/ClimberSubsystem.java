package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Isaac, Implement this however you want.
public class ClimberSubsystem extends Subsystem
{
    private final Solenoid climber;
	
    public ClimberSubsystem()
    {
        this.climber = new Solenoid(RobotMap.Solenoid.CLIMBER_SOLENOID);
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public void doStuff()
    {

    }
}