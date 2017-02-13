package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.GearSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class GearCommand extends Command
{
    private final GearSubsystem gear;
    private final boolean on;

    public GearCommand(boolean on)
    {
        requires(Robot.GEAR);
        gear = Robot.GEAR;
        this.on = on;
    }

    @Override
    protected void execute()
    {
        gear.setGear(on);
    }

    @Override
    protected void initialize()
    {

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
        end();
    }
}
