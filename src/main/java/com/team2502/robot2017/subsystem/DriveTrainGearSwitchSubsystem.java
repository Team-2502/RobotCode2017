package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

@SuppressWarnings("WeakerAccess")
public class DriveTrainGearSwitchSubsystem extends Subsystem
{
    private final Solenoid switcher;

    // TODO: Change name to `lowGear` if applicable.
    private boolean highGear;

    public DriveTrainGearSwitchSubsystem()
    {
        switcher = new Solenoid(RobotMap.Solenoid.GEAR_SWITCH);
        highGear = false;
    }

    @Override
    protected void initDefaultCommand()
    {
    }

    public void switchGear()
    {
        // TODO: Stop transmission during gear switch if applicable.
//        DriveTrainSubsystem#stop();
        setGear(highGear = !highGear);
//        Timer.delay(0.1D);
//        DriveTrainSubsystem#drive();
    }

    public void setGear(boolean highGear)
    {
        switcher.set(this.highGear = highGear);
    }

    public boolean getGear()
    {
        return highGear;
    }
}
