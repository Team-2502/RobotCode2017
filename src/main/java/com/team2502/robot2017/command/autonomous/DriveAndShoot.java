package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.DriveTimeFlywheelCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.FlywheelEncoderSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndShoot extends CommandGroup
{

    public boolean Active = true;
    protected FlywheelEncoderSubsystem e;
    private DriveTrainSubsystem driveTrain;
    protected Encoder sensor;
    protected double sensorLimit;
    protected double speed = 1;
    protected int counter = 0;
    protected boolean change = false;
    protected double initialReading;
    protected double extraTime = 0;
    protected double realSpeed = 0;
    protected double minTime = 1;
    protected boolean done = false;
    private double startTime;

    public DriveAndShoot(double speed, double time)
    {
        if (Active = true)
        {
            addSequential(new DriveTimeCommand(1.2));
            Timer.delay(1.2D);
            Active = false;
        }
        if (Active = false)
        {
            addSequential(new DriveTimeFlywheelCommand(1.2));
            done = true;

        }
    }

    @Override
    protected void initialize()
    { // TODO Auto-generated method stub

        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    { // TODO Auto-generated method stub
        if (Active = true)
        {
            addSequential(new DriveTimeCommand(1.2));
            Timer.delay(1.2D);
            Active = false;
        }
        if (Active = false)
        {
            addSequential(new DriveTimeFlywheelCommand(1.2));
            done = true;

        }
    }

    @Override
    protected boolean isFinished()
    {
        return done;

    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
        driveTrain.stop();
        e.stop();

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub
        driveTrain.stop();
        e.stop();
    }

}
