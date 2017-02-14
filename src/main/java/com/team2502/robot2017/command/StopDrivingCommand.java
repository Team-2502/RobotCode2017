package com.team2502.robot2017.command;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class StopDrivingCommand extends Command
{

    public DriveTrainSubsystem dt;

    public StopDrivingCommand()
    {
        dt.stop();
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute()
    {
        // TODO Auto-generated method stub
        dt.stop();
    }

    @Override
    protected void initialize()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
