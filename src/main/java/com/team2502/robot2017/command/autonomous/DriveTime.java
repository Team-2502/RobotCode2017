
package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command
{

    private double time;
    private double startTime;
    private DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;

    public DriveTime(double time)
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.DRIVE_TRAIN);
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
        dt.runMotors(1, 1);
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