package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.FeederSubsystem;
import edu.wpi.first.wpilibj.command.Command;

// TODO: Add proper implementation
public class FeederInBoxCommand extends Command
{
    private final FeederSubsystem FeederSubsystem;

    public FeederInBoxCommand()
    {
        requires(Robot.FEEDERINBOX);
        FeederSubsystem = Robot.FEEDERINBOX;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() { FeederSubsystem.activeDrive(); }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { FeederSubsystem.stop(); }

    @Override
    protected void interrupted() { FeederSubsystem.stop(); }
}

