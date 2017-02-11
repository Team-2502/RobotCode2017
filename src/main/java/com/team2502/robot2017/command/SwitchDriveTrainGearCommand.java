package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainGearSwitchSubsystem;
//import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchDriveTrainGearCommand extends Command
{
    private final DriveTrainGearSwitchSubsystem driveTrainGearSwitchSubsystem;

    public SwitchDriveTrainGearCommand()
    {
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        driveTrainGearSwitchSubsystem = Robot.DRIVE_TRAIN_GEAR_SWITCH;
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        driveTrainGearSwitchSubsystem.switchGear();
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
