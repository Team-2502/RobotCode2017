package com.team2502.robot2017;

import com.team2502.robot2017.command.GearLeverCommand;
import com.team2502.robot2017.command.GearPushBoxCommand;
import com.team2502.robot2017.command.GearPushGearCommand;
import com.team2502.robot2017.command.ShooterChangeSpeedCommand;
import com.team2502.robot2017.command.GearCommandTop;
<<<<<<< HEAD
import com.team2502.robot2017.command.ShooterCommand;
=======
import com.team2502.robot2017.command.OnlyAgitatorCommand;
>>>>>>> master
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
    
    public static final Button TOGGLE_SHOOTER = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SHOOTER_TOGGLE);
    public static final Button SHOOTER_INCREASE_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SHOOTER_INCREASE_SPEED);
    public static final Button SHOOTER_DECREASE_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SHOOTER_DECREASE_SPEED);
    public static final Button FEEDER = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.FEEDER);
    public static final Button ACTIVE_FORWARDS = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.ACTIVE_FORWARDS);
    public static final Button ACTIVE_BACKWARDS = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.ACTIVE_BACKWARDS);

    public static final Button SWITCH_DRIVE_TRANSMISSION = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION);
    public static final Button TOP_GEAR_BOX = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.TOP_GEAR_BOX);
    public static final Button RELEASE_GEAR = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.RELEASE_GEAR);
    public static final Button PUSH_BOX = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.PUSH_BOX);
    public static final Button PUSH_GEAR = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.PUSH_GEAR);
<<<<<<< HEAD
    public static final Button CLIMB = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.CLIMB);
=======
    public static final Button ONLY_AGITATOR = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.ONLY_AGITATOR);
>>>>>>> master

    static
    {
        SWITCH_DRIVE_TRANSMISSION.whenPressed(new SwitchDriveTransmissionCommand());
        TOP_GEAR_BOX.whenPressed(new GearCommandTop());
<<<<<<< HEAD
        RELEASE_GEAR.whenPressed(new GearLeverCommand());
        PUSH_BOX.whenPressed(new GearPushBoxCommand());
        PUSH_GEAR.whenPressed(new GearPushGearCommand());
        
        TOGGLE_SHOOTER.whenPressed(new ShooterCommand());
        FEEDER.whileHeld(new ShooterCommand());
        SHOOTER_INCREASE_SPEED.whenPressed(new ShooterChangeSpeedCommand(10));
        SHOOTER_DECREASE_SPEED.whenPressed(new ShooterChangeSpeedCommand(-10));
=======
        RELEASE_GEAR.whenPressed(new GearCommandLever());
        PUSH_BOX.whenPressed(new GearCommandPushBox());
        PUSH_GEAR.whenPressed(new GearCommandPushGear());
        ONLY_AGITATOR.whileHeld(new OnlyAgitatorCommand());
>>>>>>> master
    }

    public static void init() {}

    private OI() {}
}
