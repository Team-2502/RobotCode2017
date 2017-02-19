package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

//import edu.wpi.first.wpilibj.DoubleSolenoid;

@SuppressWarnings("WeakerAccess")
public class DriveTrainTransmissionSubsystem extends Subsystem
{
    private static Solenoid switcher;

    // TODO: Change name to `lowGear` if applicable.
    private static boolean highGear;

    public DriveTrainTransmissionSubsystem()
    {
        switcher = new Solenoid(RobotMap.Solenoid.TRANSMISSION_SWITCH);
        highGear = false;
    }

    @Override
    protected void initDefaultCommand()
    {
        /* NO-OP */
    }

    public void switchGear()
    {
        setGear(highGear = !highGear);
    }

    public boolean getGear()
    {
        return highGear;
    }

    public void setGear(boolean highGear)
    {
        switcher.set(this.highGear = highGear);
    }
}
