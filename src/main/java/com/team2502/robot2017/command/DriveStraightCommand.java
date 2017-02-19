package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;
import com.team2502.robot2017.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import logger.Log;

/**
 *
 */
@SuppressWarnings("WeakerAccess")
public class DriveStraightCommand extends Command implements PIDOutput
{

    private static final double kI = 0.0D;
    private static final double kD = 0.0D;
    private static final double kF = 0.0D;
    private static final double kToleranceDegrees = 1.0D;
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system. Note that the */
    /* SmartDashboard in Test mode has support for helping you tune */
    /* controllers by displaying a form where you can enter new P, I, */
    /* and D constants and test the mechanism. */
    private static double kP = 0.04D;


    private DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;
    private double angle;
    private PIDController turnController;
    private double rotateToAngleRate;
    private double startTime;

    private ShooterSubsystem encoder;
    private Encoder sensor;
    private double sensorLimit;
    private double speed = 1;
    private int counter = 0;
    private boolean change = false;
    private double initialReading;
    private double extraTime = 0;
    private double realSpeed = 0;
    private double minTime = 1;
    private boolean done = false;
    private boolean insideRange = true;
    private int insideRangeCounter = 0;
    private DriveTrainSubsystem driveTrain;

    public DriveStraightCommand(double angle, double speed)
    {
        requires(Robot.SHOOTER);
        requires(Robot.DRIVE_TRAIN);
        encoder = Robot.SHOOTER;
        driveTrain = Robot.DRIVE_TRAIN;
        this.angle = angle;
        this.speed = speed;
    }

    public DriveStraightCommand(double angle, double speed, boolean change)
    {
        this(angle, speed);
        this.change = change;
    }

    public DriveStraightCommand(double angle, double speed, double extraTime)
    {
        this(angle, speed);
        this.extraTime = extraTime;
    }

    public DriveStraightCommand(double angle, double speed, boolean change, double extraTime)
    {
        this(angle, speed, change);
        this.extraTime = extraTime;
    }

    public DriveStraightCommand(double angle, double speed, double extraTime, double minTime)
    {
        this(angle, speed, extraTime);
        this.minTime = minTime;
    }

    public DriveStraightCommand(double angle, double speed, boolean change, double extraTime, double minTime)
    {
        this(angle, speed, extraTime, minTime);
        this.change = change;
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {
        driveTrain.runMotors(realSpeed, -realSpeed);
        Log.debug("Normal Straight");

        realSpeed += .08;

        if(realSpeed > speed)
        {
            realSpeed = speed;
        }
        Log.debug("Goal: " + sensorLimit);

        // if (s.getSensorDistance(sensor) < RobotMap.LONG_SENSOR_RANGE_LIMITS)
        // {
        //     ++insideRangeCounter;
        // }
        // else
        // {
        //     insideRangeCounter = 0;
        // }
        //
        // if (insideRangeCounter > 2)
        // {
        //     insideRange = true;
        // }

        Log.debug("In Range: " + insideRange);
//        if((Math.abs(s.getSensorDistance(sensor)) < (RobotMap.LONG_SENSOR_RANGE_LIMITS + 0.1)) && (Math.abs(s.getSensorDistance(sensor)) > .9))
//        {
//            insideRange = true;
//        }
//        else
//        {
//            insideRange = false;
//        }

        if(counter > 2)
        {
            done = true;
        }
    }

    @Override
    protected boolean isFinished()
    {
        // return Sensors.ahrs.getFusedHeading() < 190 ||
        // Sensors.ahrs.getFusedHeading() > 170;
        Log.debug("Counter: " + counter);

        // TODO: Fix Grammar - Issac
        /*
           Hear look here! Try uncommenting this line to test a safety to not run into
           a wall - uses short sensor, so not sure where this is going to be mounted, but hopefully
           the line below this would make the robot not cause a driver station to be knocked off
           of the platform only triggers if 5 seconds have passed just to be extra safe
         */
//        if(((System.currentTimeMillis() - startTime) > 5000) && (s.getSensorDistance(Sensor.FrontShort) < 1.3) && (s.getSensorDistance(Sensor.FrontShort) > .8)) { return true; }

        return done && ((System.currentTimeMillis() - startTime) > (minTime * 1000));
    }

    @Override
    protected void end()
    {
        Scheduler.getInstance().add(new WaitCommand(10.0D));
        dt.stopDriveS();
    }

    @Override
    protected void interrupted()
    {
        dt.stopDriveS();
    }

    @Override
    public void pidWrite(double output)
    {
        rotateToAngleRate = output;
    }

    public void setPIDSource(PIDSource source)
    {
        turnController = new PIDController(kP, kI, kD, kF, source, this);
        turnController.setInputRange(-15.0, 15.0);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(false);
    }
}