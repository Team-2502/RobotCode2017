package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class FlywheelCommand extends Command
{

    private final ShooterSubsystem shooterSubsystem;
	private long runTime;
	private long startTime;


    /**
     * @param runTime Time to run for in milliseconds.
     */
    public FlywheelCommand(long runTime)
    {

        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;
        this.runTime = runTime;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public FlywheelCommand(double runTime)
    {
        this((long) (runTime * 1000));
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();

        shooterSubsystem.isFlywheelActive = false;
        shooterSubsystem.isFeederActive = false;
        
//        shooterSubsystem.defaultShooter();
    }

    @Override
    protected void execute()
    {

        shooterSubsystem.flywheelDrive();
    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > runTime;
    }

    @Override
    protected void end()
    {
        shooterSubsystem.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}