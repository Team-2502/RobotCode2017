package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class FlywheelCommand extends Command
{
    private final ShooterSubsystem shooterSubsystem;

    public FlywheelCommand()
    {
        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;
    }

    @Override
    protected void initialize()
    {
        shooterSubsystem.isFlywheelActive = false;
        shooterSubsystem.isFeederActive = false;
    }

    @Override
    protected void execute()
    {
        shooterSubsystem.flywheelDrive();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        shooterSubsystem.stop();
    }

    @Override
    protected void interrupted()
    {
        shooterSubsystem.stop();
    }
}
