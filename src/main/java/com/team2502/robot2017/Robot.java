package com.team2502.robot2017;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.command.MoveXDistance;
import com.team2502.robot2017.command.autonomous.AutoCommandG2;
import com.team2502.robot2017.command.autonomous.AutoCommandG3;
import com.team2502.robot2017.command.autonomous.AutoVCommand;
import com.team2502.robot2017.subsystem.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import logger.Log;
import com.kauailabs.navx.frc.AHRS;


@SuppressWarnings({ "WeakerAccess", "unused" })
public final class Robot extends IterativeRobot
{
	// Makes all the stuff
    public static final DriveTrainSubsystem DRIVE_TRAIN = new DriveTrainSubsystem();
    public static final PressureSensorSubsystem PRESSURE_SENSOR = new PressureSensorSubsystem();
    public static final VisionSubsystem VISION = new VisionSubsystem();
    public static final Compressor COMPRESSOR = new Compressor();
    public static final ShooterSubsystem SHOOTER = new ShooterSubsystem();
    public static final DistanceSensorSubsystem DISTANCE_SENSOR = new DistanceSensorSubsystem();
    public static final ActiveIntakeSubsystem ACTIVE = new ActiveIntakeSubsystem();
    public static final GearBoxSubsystem GEAR_BOX = new GearBoxSubsystem();
    public static final DriveTrainTransmissionSubsystem DRIVE_TRAIN_GEAR_SWITCH;
    public static final ClimberSubsystem CLIMBER = new ClimberSubsystem();

    // NavX Subsystem
    public static final AHRS NAVX = new AHRS(SPI.Port.kMXP);

    static
    {
        /* I don't know why but this prevents problems. */
        DRIVE_TRAIN_GEAR_SWITCH = new DriveTrainTransmissionSubsystem();
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        Log.createLogger(true);
        DashboardData.setup();
        OI.init();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {}

    public void disabledPeriodic()
    {
        DashboardData.update();
        DRIVE_TRAIN.stop();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
     * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
     * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
     * below the Gyro
     * <p>
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
     * or additional comparisons to the switch structure below with additional strings and commands.
     */
    public void autonomousInit()
    {
        Scheduler.getInstance().add(new AutoCommandG2());
        
    }

    /**
     * 
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
    }

    public void teleopInit()
    {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
        LiveWindow.run();
        DashboardData.update();
    }
}