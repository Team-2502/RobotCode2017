package com.team2502.robot2017.subsystem;

import com.sun.org.apache.xpath.internal.operations.Variable;
import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.DriveCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TrajectoryPoint;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
import javafx.beans.NamedArg;
import logger.Log;

import java.io.Serializable;

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
    
    public int millisecondsToRunTL = 1000;
    public int millisecondsToRunTR = 1000; 
    
    public int m = 1000;

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

    private Pair<Double, Double> getSpeedArcade()
    {
        double leftSpeed;
        double rightSpeed;
        // Get the base speed of the robot
        double yLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();

//        // Only increase the speed by a small amount
//        double diff = yLevel - lastRight;
//        if(diff > 0.1D) { yLevel = lastRight + 0.1D; }
//        else if(diff < 0.1D) { yLevel = lastRight - 0.1D; }
//        lastRight = yLevel;

        leftSpeed = yLevel;
        rightSpeed = yLevel;

        double xLevel = -OI.JOYSTICK_DRIVE_RIGHT.getX();
        
        // Should invert the left/right to be more intuitive while driving backwards.
        if(yLevel < 0) { xLevel = -xLevel;}
        
        if(xLevel > 0.0D) { leftSpeed -= xLevel; }
        else if(xLevel < 0.0D) { rightSpeed += xLevel; }
//        Log.debug("Y: " + yLevel);
//        Log.debug("X: " + xLevel);
//        Log.debug("L: " + leftSpeed);
//        Log.debug("R: " + rightSpeed);
//        Log.debug("\n\n");

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(leftSpeed) < 0.05D) { leftSpeed = 0.0D; }
        if(Math.abs(rightSpeed) < 0.05D) { rightSpeed = 0.0D; }

        return new Pair<Double, Double>(leftSpeed, rightSpeed);
    }

    /**
     * Used to gradually increase the speed of the robot.
     *
     * @param isLeftSide Whether or not it is the left joystick/side
     * @return the speed of the robot
     */
    private Pair<Double, Double> getSpeed()
    {
        double joystickLevel = 0.0D;
        // Get the base speed of the robot
        joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY();

        // Only increase the speed by a small amount
        double diff = joystickLevel - lastLeft;
        if(diff > 0.1D) { joystickLevel = lastLeft + 0.1D; }
        else if(diff < 0.1D) { joystickLevel = lastLeft - 0.1D; }
        lastLeft = joystickLevel;

        Pair<Double, Double> out = new Pair<Double, Double>(joystickLevel, 0.0D);

        joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();

        diff = joystickLevel - lastRight;
        if(diff > 0.1D) { joystickLevel = lastRight + 0.1D; }
        else if(diff < 0.1D) { joystickLevel = lastRight - 0.1D; }
        lastRight = joystickLevel;

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(joystickLevel) < 0.05D) { joystickLevel = 0.0D; }

        out.right = joystickLevel;

        return out;
    }

    public void drive()
    {
        Pair<Double, Double> speed = (DashboardData.getDriveType() == DriveTypes.DUAL_STICK) ? getSpeed() : getSpeedArcade();
        drive.tankDrive(speed.left, speed.right, true);
    }
    
    public void drive(double x, double y, double z)
    {
    	leftTalon0.set(x);
		leftTalon1.set(x);
		rightTalon0.set(y);
		rightTalon1.set(y);
		Timer.delay(z);
    	leftTalon0.set(0.0D);
		leftTalon1.set(0.0D);
		rightTalon0.set(0.0D);
		rightTalon1.set(0.0D);
    }
    
    public void stopDriveS()
    {
    	 leftTalon0.set(0);
    	 leftTalon1.set(0);
    	 rightTalon0.set(0);
    	 rightTalon1.set(0);
    }
    
    
    public void stop()
    {
        lastLeft = 0.0D;
        lastRight = 0.0D;
        drive.tankDrive(0.0D, 0.0D);
        Timer.delay(0.3D);
    }

    public enum DriveTypes
    {
        DUAL_STICK,
        ARCADE;
    }

    public static class Pair<L, R> implements Serializable
    {
        public L left;
        public R right;

        private String nameL;
        private String nameR;

        public Pair(L left, R right)
        {
            this.left = left;
            this.right = right;
            this.nameL = left.getClass().getSimpleName();
            this.nameR = right.getClass().getSimpleName();
        }

        public Pair() {}

        @Override
        public String toString()
        {
            return new StringBuilder(100 + nameL.length() + nameR.length()).append("Pair<").append(nameL).append(',').append(nameR).append("> { \"left\": \"").append(left).append("\", \"right\": \"").append(right).append("\" }").toString();
        }

        @Override
        public int hashCode()
        {
            return (left.hashCode() * 13) + ((right == null) ? 0 : right.hashCode());
        }

        @Override
        public boolean equals(Object o)
        {
            if(this == o) { return true; }
            if(o instanceof Pair)
            {
                Pair pair = (Pair) o;
                return ((left != null) ? left.equals(pair.left) : pair.left == null) && ((left != null) ? left.equals(pair.left) : pair.left == null);
            }
            return false;
        }
    }
}