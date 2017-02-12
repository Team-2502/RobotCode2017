package com.team2502.robot2017;

import com.team2502.robot2017.command.autonomous.AutoCommandG;
import com.team2502.robot2017.command.autonomous.VisionAuto;
import com.team2502.robot2017.command.DriveTimeFlywheelCommand;
import com.team2502.robot2017.subsystem.ActiveIntakeSubsystem;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainGearSwitchSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;
import com.team2502.robot2017.subsystem.IsaacsSpecialSubsystem;
import com.team2502.robot2017.subsystem.PressureSensorSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import logger.Log;

@SuppressWarnings({ "WeakerAccess" })
public final class Robot extends IterativeRobot
{
    static
    {
        /* I don't know why but this prevents problems. */
        DRIVE_TRAIN_GEAR_SWITCH = new DriveTrainGearSwitchSubsystem();
    }

    public static final DriveTrainSubsystem DRIVE_TRAIN = new DriveTrainSubsystem();
    public static final PressureSensorSubsystem PRESSURE_SENSOR = new PressureSensorSubsystem();
    public static final VisionSubsystem VISION = new VisionSubsystem();
    public static final Compressor COMPRESSOR = new Compressor();
    public static final FlywheelEncoderSubsystem ENCODER = new FlywheelEncoderSubsystem();
    public static final DistanceSensorSubsystem DISTANCE_SENSOR = new DistanceSensorSubsystem();
    public static final DriveTimeFlywheelCommand DRIVETIMEFL = new DriveTimeFlywheelCommand(1.2);
    public static final AutoCommandG AUTO = new AutoCommandG();
    public static final ActiveIntakeSubsystem ACTIVE = new ActiveIntakeSubsystem();
    public static final IsaacsSpecialSubsystem GEAR = new IsaacsSpecialSubsystem();

    public static DriveTrainGearSwitchSubsystem DRIVE_TRAIN_GEAR_SWITCH;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
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
    @Override
    public void disabledInit()
    {
    }

    @Override
    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
        DRIVE_TRAIN.stop();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings and commands.
     */
    @Override
    public void autonomousInit()
    {
//        AutonomousCommand.autonomousInit();
//        AutonomousCommand.startS();
        Scheduler.getInstance().add(new AutoCommandG());

    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
    }

    @Override
    public void teleopInit()
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        AutonomousCommand.cancelS();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic()
    {
        LiveWindow.run();
        DashboardData.update();
    }
}
