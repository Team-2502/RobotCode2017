
package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeFlywheel extends Command
{

    private double time;
    private double startTime;
    private DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;
    private FlywheelEncoderSubsystem FL = Robot.ENCODER;

    public DriveTimeFlywheel(double time)
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.ENCODER);
        this.time = time;
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
        FL.FlywheelEncoderSubsystem();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > time * 1000;
    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {
        dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {
    }
}