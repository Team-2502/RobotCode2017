package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import logger.Log;

@SuppressWarnings("WeakerAccess")
public class EncDriveToDistanceCommand extends Command
{
    private DriveTrainSubsystem driveTrain;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in seconds.
     */
    public EncDriveToDistanceCommand()
    {
    	driveTrain = Robot.DRIVE_TRAIN;
    	requires(driveTrain);
    }

    @Override
    protected void initialize()
    {
        driveTrain.setAutonSettings(driveTrain.leftTalon0);
        driveTrain.setAutonSettings(driveTrain.rightTalon1);
    }

    @Override
    protected void execute()
    {
    	driveTrain.leftTalon0.set(-4.6);
        driveTrain.rightTalon1.set(4.6);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
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