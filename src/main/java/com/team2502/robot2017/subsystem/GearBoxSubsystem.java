package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Isaac, Implement this however you want.
public class GearBoxSubsystem extends Subsystem
{
    private final Solenoid solenoid0;
    private final Solenoid solenoid1;
    private final Solenoid solenoid2;
    private final Solenoid solenoid3;

    public GearBoxSubsystem()
    {
        this.solenoid0 = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID0);
        this.solenoid1 = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID1);
        this.solenoid2 = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID2);
        this.solenoid3 = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID3);
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public void doStuff()
    {

    }
}