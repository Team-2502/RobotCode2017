package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ActiveIntakeSubsystem;
import edu.wpi.first.wpilibj.command.Command;

// TODO: Add proper implementation
public class ActiveCommand extends Command
{
    private final ActiveIntakeSubsystem activeIntakeSubsystem;

    public ActiveCommand()
    {
        requires(Robot.ACTIVE);
        activeIntakeSubsystem = Robot.ACTIVE;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() { activeIntakeSubsystem.activeDrive(); }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { activeIntakeSubsystem.stop(); }

    @Override
    protected void interrupted() { activeIntakeSubsystem.stop(); }
}

