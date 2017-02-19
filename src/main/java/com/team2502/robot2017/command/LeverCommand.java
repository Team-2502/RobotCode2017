package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import com.team2502.robot2017.subsystem.GearBoxSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class LeverCommand extends Command
{
    private final DriveTrainTransmissionSubsystem gear;
    private final boolean on;

    public LeverCommand(boolean on)
    {
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        gear = Robot.DRIVE_TRAIN_GEAR_SWITCH;
        this.on = on;
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        gear.setGear(on);
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