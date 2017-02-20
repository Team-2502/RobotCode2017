package com.team2502.robot2017;

import com.team2502.robot2017.command.GearCommandLever;
import com.team2502.robot2017.command.GearCommandPushBox;
import com.team2502.robot2017.command.GearCommandPushGear;
import com.team2502.robot2017.command.GearCommandTop;
import com.team2502.robot2017.command.SwitchDriveTransmissionCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

@SuppressWarnings("WeakerAccess")
public final class OI
{
    public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public static final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);
    public static final Button SWITCH_DRIVE_TRANSMISSION = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION);
    public static final Button TOP_GEAR_BOX = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.TOP_GEAR_BOX);
    public static final Button RELEASE_GEAR = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.RELEASE_GEAR);
    public static final Button PUSH_BOX = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.PUSH_BOX);
    public static final Button PUSH_GEAR = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.PUSH_GEAR);

    static
    {
        SWITCH_DRIVE_TRANSMISSION.whenPressed(new SwitchDriveTransmissionCommand());
        TOP_GEAR_BOX.whenPressed(new GearCommandTop());
        RELEASE_GEAR.whenPressed(new GearCommandLever());
        PUSH_BOX.whenPressed(new GearCommandPushBox());
        PUSH_GEAR.whenPressed(new GearCommandPushGear());
        
    }

    public static void init() {}

    private OI() {}
}
