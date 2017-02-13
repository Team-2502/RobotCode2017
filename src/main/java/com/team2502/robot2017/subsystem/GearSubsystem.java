package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Isaac, Implement this however you want.
public class GearSubsystem extends Subsystem
{
    private final Solenoid moveTop;
    private final Solenoid lever;
    private final Solenoid pushGear;

    private boolean buttonPressed0 = false;
    private boolean mode0 = false;
    private boolean buttonPressed1 = false;
    private boolean mode1 = false;
    private boolean buttonPressed2 = false;
    private boolean mode2 = false;

    public GearSubsystem()
    {
        this.moveTop = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID0);
        this.lever = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID1);
        this.pushGear = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID2);
    }

    @Override
    protected void initDefaultCommand()
    {
    }

    public void doStuff()
    {
        if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(4) && !buttonPressed0)
        {
            mode0 = !mode0;
        }
        buttonPressed0 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(4);

        moveTop.set(mode0);

        if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3) && !buttonPressed1)
        {
            mode1 = !mode1;
        }
        buttonPressed1 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3);

        lever.set(mode1);

        if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5) && !buttonPressed2)
        {
            mode2 = !mode2;
        }
        buttonPressed2 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5);

        pushGear.set(mode2);
    }

    public void pushGear()
    {
        pushGear.set(true);
    }

    public void pullGear()
    {
        pushGear.set(false);
    }

    public void setGear(boolean on)
    {
        pushGear.set(on);
    }

    public void leverOn()
    {
        lever.set(true);
    }

    public void leverOff()
    {
        lever.set(false);
    }

    public void setLever(boolean on)
    {
        lever.set(on);
    }
}
  