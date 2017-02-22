package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;


@SuppressWarnings("WeakerAccess")
public class AutoVCommand extends Command
{
	
	private DistanceSensorSubsystem distanceSensorSubsystem;
    public static DriveTrainSubsystem dt;
    public double offset;
    public double leftSpeed;
    public double rightSpeed;
    public boolean inFrontOfGear = false;
    public boolean Reverse;

    public AutoVCommand(boolean RevOrFor)
    {
        dt = new DriveTrainSubsystem();
        leftSpeed = 0.5;
        rightSpeed = -0.5;
        Reverse = RevOrFor;
       
    }

    @Override
    protected void initialize()
    {

    }


    @Override
    protected void interrupted()
    {

    }

    @Override
    protected void execute()
    {
        if((double) distanceSensorSubsystem.getSensorDistance() > 26 && (double)distanceSensorSubsystem.getSensorDistance() < 32)
        {
        	offset = VisionSubsystem.getOffsetCam1();
        if(!(offset == 1023) && ((offset > 5) || (offset < -5)))
        	{
        		offset = offset / 100;
        	if (Reverse)
        	{
        		leftSpeed += -offset;
        		rightSpeed += -offset;        		
        	}
        	else
        	{
        		leftSpeed += offset;
        		rightSpeed += offset;
        	}
        	dt.runMotors(leftSpeed, rightSpeed);
        }
        else if((offset > -5) || (offset < 5))
        {
        leftSpeed = 0.5;
        rightSpeed = -0.5;
        dt.runMotors(leftSpeed, rightSpeed);
        inFrontOfGear = true;
        }
        
       }
    }

    @Override
    protected boolean isFinished()
    {
        return inFrontOfGear;
    }

    protected void end()
    {
        dt.stop();
    }
}



