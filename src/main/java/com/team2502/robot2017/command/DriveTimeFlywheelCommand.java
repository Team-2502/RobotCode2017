package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class DriveTimeFlywheelCommand extends Command
{
    private DriveTrainSubsystem driveTrain;
    private FlywheelEncoderSubsystem flyWheelEncoder;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public DriveTimeFlywheelCommand(long runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.ENCODER);
        driveTrain = Robot.DRIVE_TRAIN;
        flyWheelEncoder = Robot.ENCODER;

        this.runTime = runTime;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public DriveTimeFlywheelCommand(double runTime)
    {
        this((long) (runTime * 1000));
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {
        flyWheelEncoder.FlywheelEncoderSubsystem();
    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > runTime;
    }

    @Override
    protected void end()
    {
        driveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}