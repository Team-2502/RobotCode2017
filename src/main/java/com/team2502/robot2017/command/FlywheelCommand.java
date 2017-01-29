package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class FlywheelCommand extends Command
{
    private final FlywheelEncoderSubsystem flywheelEncoderSubsystem;

    public FlywheelCommand()
    {
        requires(Robot.ENCODER);
        flywheelEncoderSubsystem = Robot.ENCODER;
    }

    @Override
    protected void initialize()
    {
        flywheelEncoderSubsystem.isActive = false;
    }

    @Override
    protected void execute()
    {
        flywheelEncoderSubsystem.flywheelDrive();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        flywheelEncoderSubsystem.stop();
        flywheelEncoderSubsystem.isActive = false;
    }

    @Override
    protected void interrupted()
    {
        flywheelEncoderSubsystem.stop();
        flywheelEncoderSubsystem.isActive = false;
    }
}
