package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveStraightCommand extends Command implements PIDOutput
{

    protected static final double kI = 0.00;
    protected static final double kD = 0.00;
    protected static final double kF = 0.00;
    protected static final double kToleranceDegrees = 1.0f;

    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system. Note that the */
    /* SmartDashboard in Test mode has support for helping you tune */
    /* controllers by displaying a form where you can enter new P, I, */
    /* and D constants and test the mechanism. */
    protected static double kP = 0.04;
    protected DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;
    protected double angle;
    protected PIDController turnController;
    protected double rotateToAngleRate;
    protected double startTime;

    protected FlywheelEncoderSubsystem e;
    protected Encoder sensor;
    protected double sensorLimit;
    protected double speed = 1;
    protected int counter = 0;
    protected boolean change = false;
    protected double initialReading;
    protected double extraTime = 0;
    protected double realSpeed = 0;
    protected double minTime = 1;
    protected boolean done = false;
    protected boolean insideRange = true;
    protected int insideRangeCounter = 0;
    private DriveTrainSubsystem driveTrain;

    public DriveStraightCommand(double angle, double speed)
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.ENCODER);
        requires(Robot.DRIVE_TRAIN);
        e = Robot.ENCODER;
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
        this(angle, speed);
        this.extraTime = extraTime;
        this.change = change;
    }

    public DriveStraightCommand(double angle, double speed, double extraTime, double minTime)
    {
        this(angle, speed);
        this.extraTime = extraTime;
        this.minTime = minTime;
    }

    public DriveStraightCommand(double angle, double speed, boolean change, double extraTime, double minTime)
    {
        this(angle, speed);
        this.extraTime = extraTime;
        this.minTime = minTime;
        this.change = change;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {

        driveTrain.runMotors(realSpeed, -realSpeed);
        System.out.println("Normal Straight");

        realSpeed += .08;

        if(realSpeed > speed)
        {
            realSpeed = speed;
        }
        System.out.println("Goal: " + sensorLimit);

        // if (s.getSensorDistance(sensor) < RobotMap.LONG_SENSOR_RANGE_LIMITS)
        // {
        // insideRangeCounter++;
        // } else {
        // insideRangeCounter = 0;
        // }
        //
        // if (insideRangeCounter > 2) {
        // insideRange = true;
        // }

        System.out.println("In Range: " + insideRange);
        if(insideRange)
        {

        }
        // if (Math.abs(s.getSensorDistance(sensor)) <
        // RobotMap.LONG_SENSOR_RANGE_LIMITS+.1 &&
        // Math.abs(s.getSensorDistance(sensor)) > .9) {
        // insideRange = true;
        // } else {
        // insideRange = false;
        // }

        if(counter > 2)
        {
            done = true;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        // return Sensors.ahrs.getFusedHeading() < 190 ||
        // Sensors.ahrs.getFusedHeading() > 170;
        System.out.println("Counter: " + counter);

        // HEY LOOK HERE!!!!! TRY UNCOMMENTING THIS LINE TO TEST A SAFETY TO NOT
        // RUN INTO
        // A WALL - USES SHORT SENSOR, SO NOT SURE WHERE GOING TO BE MOUNTED,
        // BUT HOPEFULLY
        // THE LINE BELOW THIS WOULD MAKE THE ROBOT NOT CAUSE A DRIVER STATION
        // TO BE
        // KNOCKED OFF OF THE PLATFORM THINGY
        // ONLY TRIGGERS IF 5 SECONDS HAVE PASSED JUST TO BE EXTRA SAFE TIME
        // WISE
        // if (System.currentTimeMillis() - startTime > 5000 &&
        // s.getSensorDistance(Sensor.FrontShort) < 1.3 &&
        // s.getSensorDistance(Sensor.FrontShort) > .8) return true;

        return done && System.currentTimeMillis() - startTime > minTime * 1000;

    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {

        Scheduler.getInstance().add(new WaitCommand(10.0D));
        dt.stopDriveS();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {
        dt.stopDriveS();
    }

    @Override
    public void pidWrite(double output)
    {
        // TODO Auto-generated method stub
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