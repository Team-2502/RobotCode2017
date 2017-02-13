package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class TurnRightCommand extends Command
{
    private DriveTrainSubsystem driveTrain;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public TurnRightCommand(long runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        this.runTime = runTime;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public TurnRightCommand(double runTime)
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
        driveTrain.runMotors(1.0D, 1.0D);
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
        driveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}