package com.team2502.robot2017.subsystems;

import com.team2502.robot2017.OI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrainSubsystem extends Subsystem
{
    private double m_lastLeft;
    private double m_lastRight;

    @Override
    protected void initDefaultCommand()
    {
        m_lastLeft = 0.0D;
        m_lastRight = 0.0D;
    }

    /**
     * Used to gradually increase the speed of the robot.
     *
     * @param left Whether or not it is the left joystick
     * @return the speed of the robot
     */
    private double getSpeed(boolean left)
    {
        double speed;
        // Get the base speed of the robot
        if(left)
        {
            speed = -OI.getInstance().JOYSTICK_DRIVE_LEFT.getY();
        } else
        {
            speed = -OI.getInstance().JOYSTICK_DRIVE_RIGHT.getY();
        }

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(speed) < 0.05D)
        {
            speed = 0.0D;
        }

        // Only increase the speed by a small amount
        if(left)
        {
            double diff = speed - m_lastLeft;
            if(diff > 0.1D)
            {
                speed = m_lastLeft + 0.1D;
            } else if(diff < 0.1D)
            {
                speed = m_lastLeft - 0.1D;
            }
        } else
        {
            double diff = speed - m_lastRight;
            if(diff > 0.1D)
            {
                speed = m_lastRight + 0.1D;
            } else if(diff < 0.1D)
            {
                speed = m_lastRight - 0.1D;
            }
        }

        return speed;
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
        double leftSpeed  = getLeftSpeed();
        double rightSpeed = getRightSpeed();
    }
}
