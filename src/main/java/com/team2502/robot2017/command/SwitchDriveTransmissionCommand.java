package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchDriveTransmissionCommand extends Command
{
    private final DriveTrainTransmissionSubsystem driveTrainTransmissionSubsystem;

    public SwitchDriveTransmissionCommand()
    {
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        driveTrainTransmissionSubsystem = Robot.DRIVE_TRAIN_GEAR_SWITCH;
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        driveTrainTransmissionSubsystem.switchGear();
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {

    }

    @Override
    protected void interrupted()
    {

    }
}
