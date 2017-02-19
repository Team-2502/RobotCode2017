package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import com.team2502.robot2017.subsystem.GearBoxSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class GearCommand extends Command
{
    private final GearBoxSubsystem gear;

    public GearCommand()
    {
        requires(Robot.GEAR_BOX);
        gear = Robot.GEAR_BOX;
    }

    @Override
    protected void execute()
    {
        gear.doStuff();
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected boolean isFinished() { return true; }

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
