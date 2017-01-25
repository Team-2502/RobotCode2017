package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;

    public DriveCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        driveTrainSubsystem.drive();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        driveTrainSubsystem.stop();
    }

    @Override
    protected void interrupted()
    {
        driveTrainSubsystem.stop();
    }
}
