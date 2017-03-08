package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.FeederSubsystem;
import com.team2502.robot2017.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

// TODO: Add proper implementation
public class OnlyAgitatorCommand extends Command
{
    private final ShooterSubsystem ShooterSubsystem;

    public OnlyAgitatorCommand()
    {
        requires(Robot.SHOOTER);
        ShooterSubsystem = Robot.SHOOTER;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() { ShooterSubsystem.onlySpinAgitator(); }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { ShooterSubsystem.stopFeeder(); }

    @Override
    protected void interrupted() { end(); }
}