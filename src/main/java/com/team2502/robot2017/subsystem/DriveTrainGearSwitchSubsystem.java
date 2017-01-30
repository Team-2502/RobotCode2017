package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrainGearSwitchSubsystem extends Subsystem
{

    private final DoubleSolenoid leftGear;
    private final DoubleSolenoid rightGear;

    // TODO: Change name to `lowGear` if applicable.
    private boolean highGear;

    public DriveTrainGearSwitchSubsystem()
    {
        leftGear = new DoubleSolenoid(RobotMap.Solenoid.HIGH_GEAR, RobotMap.Solenoid.LOW_GEAR);
        rightGear = new DoubleSolenoid(RobotMap.Solenoid.HIGH_GEAR, RobotMap.Solenoid.LOW_GEAR);

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
        this.highGear = highGear;
        DoubleSolenoid.Value v = highGear ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse;
        leftGear.set(v);
        rightGear.set(v);
    }

    public boolean getGear()
    {
        return highGear;
    }
}
