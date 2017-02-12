
package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class FlywheelCommand extends Command
{
    private FlywheelEncoderSubsystem flywheel;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public FlywheelCommand(long runTime)
    {
        requires(Robot.ENCODER);
        flywheel = Robot.ENCODER;
        this.runTime = runTime;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public FlywheelCommand(double runTime)
    {
        this((long) (runTime * 1000));
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
       flywheel.isActiveFlywheel = false;
       flywheel.isActiveFeeder = false;
    }

    @Override
    protected void execute()
    {
        flywheel.flywheelDrive();
//    	addSequential(new DriveTimeCommand(1.2));
//        addSequential(new DriveTimeFlywheelCommand(2D));
    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > runTime;
    }

    @Override
    protected void end()
    {
        flywheel.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}