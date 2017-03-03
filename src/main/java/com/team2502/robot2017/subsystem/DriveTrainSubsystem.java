package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.DriveCommand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import logger.Log;

/**
 * Example Implementation, Many changes needed.
 */
@SuppressWarnings("FieldCanBeLocal")
public class DriveTrainSubsystem extends Subsystem
{
    private static final Pair<Double, Double> SPEED_CONTAINER = new Pair<Double, Double>();

    private final CANTalon leftTalon0;
    private final CANTalon leftTalon1;
    private final CANTalon rightTalon0;
    private final CANTalon rightTalon1;
    private final RobotDrive drive;
    private double lastLeft;
    private double lastRight;
    public double leftSpeed;
    public double rightSpeed;

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

        setTalonSettings(leftTalon0);
        setTalonSettings(leftTalon1);
        setTalonSettings(rightTalon0);
        setTalonSettings(rightTalon1);
    }

    private void setTalonSettings(CANTalon talon)
    {
        talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        talon.configEncoderCodesPerRev(256);
        talon.reverseSensor(false);
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new DriveCommand());
    }

    private static void debugSpeed(String format, Object... args)
    {
        Log.debug(String.format(format, args));
    }

    private int logCounter = 0;

    @SuppressWarnings({ "SuspiciousNameCombination", "PointlessBooleanExpression", "ConstantConditions" })
    private Pair<Double, Double> getSpeedArcade(Pair<Double, Double> out)
    {
        // Get the base speed of the robot
        double yLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();
        double leftSpeed = yLevel;
        double rightSpeed = yLevel;

        double xLevel = -OI.JOYSTICK_DRIVE_RIGHT.getX();

        // Should invert the left/right to be more intuitive while driving backwards.
        if(yLevel < 0.0D) { xLevel = -xLevel;}

        if(xLevel > 0.0D) { leftSpeed -= xLevel; }
        else if(xLevel < 0.0D) { rightSpeed += xLevel; }

//        if(logCounter++ % 10 == 0 && false)
//        {
//            debugSpeed("X: %d&nY: %d%nL: %d%nR: %d%n%n", yLevel, xLevel, leftSpeed, rightSpeed);
//        }

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(leftSpeed) < 0.05D) { leftSpeed = 0.0D; }
        if(Math.abs(rightSpeed) < 0.05D) { rightSpeed = 0.0D; }

        out.left = leftSpeed;
        out.right = rightSpeed;
        return out;
    }

    private Pair<Double, Double> getSpeedArcade()
    {
        return getSpeedArcade(SPEED_CONTAINER);
    }

    /**
     * Used to gradually increase the speed of the robot.
     *
<<<<<<< HEAD
     * @param isLeftSide Whether or not it is the left joystick/side
=======
     * @param out The object to store the data in
>>>>>>> master
     * @return the speed of the robot
     */
    private Pair<Double, Double> getSpeed(Pair<Double, Double> out)
    {
        // Get the base speed of the robot
        double joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY();

        // Only increase the speed by a small amount
        double diff = joystickLevel - lastLeft;
        if(diff > 0.1D)
        {
            joystickLevel = lastLeft + 0.1D;
        }
        else if(diff < 0.1D)
        {
            joystickLevel = lastLeft - 0.1D;
        }
        lastLeft = joystickLevel;

        out.left = joystickLevel;

        joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();

        diff = joystickLevel - lastRight;
        if(diff > 0.1D)
        {
            joystickLevel = lastRight + 0.1D;
        }
        else if(diff < 0.1D)
        {
            joystickLevel = lastRight - 0.1D;
        }
        lastRight = joystickLevel;

        // Sets the speed to 0 if the speed is less than 0.05 or larger than
        // -0.05
        if(Math.abs(joystickLevel) < 0.05D)
        {
            joystickLevel = 0.0D;
        }

        out.right = joystickLevel;

        return out;
    }

    private Pair<Double, Double> getSpeed()
    {
        return getSpeed(SPEED_CONTAINER);
    }

    public void drive()
    {
        Pair<Double, Double> speed = DashboardData.getDriveType() == DriveTypes.DUAL_STICK ? getSpeed()
                                                                                           : getSpeedArcade();
        
        
        drive.tankDrive(speed.left, speed.right, true);
    }

    private static final double DELAY_TIME = 5.77D + 43902.0D / 9999900.0D;

    public void runMotors(double x, double y) // double z
    {	

    	leftSpeed = x;
    	rightSpeed = y;
        leftTalon0.set(x);
        leftTalon1.set(x);
        rightTalon0.set(y);
        rightTalon1.set(y);
        // Timer.delay(DELAY_TIME);
        // Scheduler.getInstance().add(new WaitCommand(DELAY_TIME));
        // stopDriveS();
//        SmartDashboard.putNumber("Autonomous", Robot.AUTO.getTimerStraight());
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
        DUAL_STICK, ARCADE;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Pair<L, R>
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

        public Pair()
        {
        }

        @Override
        public String toString()
        {
            return new StringBuilder(100 + nameL.length() + nameR.length()).append("Pair<").append(nameL).append(',')
                                                                           .append(nameR).append("> { \"left\": \"").append(left).append("\", \"right\": \"").append(right)
                                                                           .append("\" }").toString();
        }

        @Override
        public int hashCode()
        {
            return left.hashCode() * 13 + (right == null ? 0 : right.hashCode());
        }

        @Override
        public boolean equals(Object o)
        {
            if(this == o) { return true; }
            if(o instanceof Pair)
            {
                Pair pair = (Pair) o;
                return (left != null ? left.equals(pair.left) : pair.left == null)
                       && (left != null ? left.equals(pair.left) : pair.left == null);
            }
            return false;
        }
    }
}