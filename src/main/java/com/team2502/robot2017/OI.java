package com.team2502.robot2017;

import com.team2502.robot2017.command.SwitchDriveTrainGearCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public final class OI
{
    private OI() {}

    public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public static final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);

    public static final Button SWITCH_GEAR_BUTTON = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.SWITCH_GEAR);

    static
    {
        SWITCH_GEAR_BUTTON.whenPressed(new SwitchDriveTrainGearCommand());
    }
}
