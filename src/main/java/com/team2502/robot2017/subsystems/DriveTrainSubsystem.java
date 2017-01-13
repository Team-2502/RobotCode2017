package com.team2502.robot2017.subsystems;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.DriveCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Example Implementation, Many changes needed.
 */
public class DriveTrainSubsystem extends Subsystem
{
    private double lastLeft;
    private double lastRight;

    private final CANTalon leftTalon0;
    private final CANTalon leftTalon1;
    private final CANTalon rightTalon0;
    private final CANTalon rightTalon1;

    private final RobotDrive drive;

    public DriveTrainSubsystem()
    {
        lastLeft = 0.0D;
        lastRight = 0.0D;

        leftTalon0 = new CANTalon(RobotMap.Motor.LEFT_TALON_0);
        leftTalon1 = new CANTalon(RobotMap.Motor.LEFT_TALON_1);
        rightTalon0 = new CANTalon(RobotMap.Motor.RIGHT_TALON_0);
        rightTalon1 = new CANTalon(RobotMap.Motor.RIGHT_TALON_1);

        drive = new RobotDrive(leftTalon0, leftTalon1, rightTalon0, rightTalon1);
        drive.setExpiration(0.1D);
    }

    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new DriveCommand());
    }

    /**
     * Used to gradually increase the speed of the robot.
     *
     * @param isLeftSide Whether or not it is the left joystick/side
     * @return the speed of the robot
     */
    private double getSpeed(boolean isLeftSide)
    {
        double joystickLevel = 0.0D;
        // Get the base speed of the robot
        joystickLevel = isLeftSide ? -OI.JOYSTICK_DRIVE_LEFT.getY() : -OI.JOYSTICK_DRIVE_RIGHT.getY();

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(joystickLevel) < 0.05D) { joystickLevel = 0.0D; }

        // Only increase the speed by a small amount
        if(isLeftSide)
        {
            double diff = joystickLevel - lastLeft;
            if(diff > 0.1D) { joystickLevel = lastLeft + 0.1D; }
            else if(diff < 0.1D) { joystickLevel = lastLeft - 0.1D; }
            lastLeft = joystickLevel;
        }
        else
        {
            double diff = joystickLevel - lastRight;
            if(diff > 0.1D) { joystickLevel = lastRight + 0.1D; }
            else if(diff < 0.1D) { joystickLevel = lastRight - 0.1D; }
            lastRight = joystickLevel;
        }

        return joystickLevel;
    }

    private double getLeftSpeed()
    {
        return getSpeed(true);
    }

    private double getRightSpeed()
    {
        return getSpeed(false);
    }

    public void drive()
    {
        double leftSpeed = getLeftSpeed();
        double rightSpeed = getRightSpeed();
        drive.tankDrive(leftSpeed, rightSpeed, true);
    }

    public void stop() {

        drive.tankDrive(0.0D, 0.0D);

        Timer.delay(0.3D);
    }
}
