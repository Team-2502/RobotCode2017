package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrainGearSwitchSubsystem extends Subsystem
{

    private final Solenoid leftGear;
    private final Solenoid rightGear;

    // TODO: Change name to `lowGear` if applicable.
    private boolean highGear;

    public DriveTrainGearSwitchSubsystem()
    {
        leftGear = new Solenoid(RobotMap.Solenoid.LEFT_GEAR);
        rightGear = new Solenoid(RobotMap.Solenoid.RIGHT_GEAR);

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
        leftGear.set(highGear);
        rightGear.set(highGear);
    }

    public boolean getGear()
    {
        return highGear;
    }
}
