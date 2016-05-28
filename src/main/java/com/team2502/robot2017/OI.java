package com.team2502.robot2017;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    private static OI instance = null;

    public static OI getInstance()
    {
        if(instance == null)
        {
            instance = new OI();
        }
        return instance;
    }

    public final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);
}