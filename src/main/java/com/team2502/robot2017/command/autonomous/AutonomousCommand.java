package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutonomousCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final VisionSubsystem visionSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private long startTime;

    public AutonomousCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);
        requires(Robot.SHOOTER);

        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        visionSubsystem = Robot.VISION;
        shooterSubsystem = Robot.SHOOTER;
    }
    
    public enum AutoGroup
    {
        AUTOCOMMANDG1, AUTOCOMMANDG2, AUTOCOMMANDG3, AUTOCOMMANDG4, AUTOCOMMANDG5;
    }
    
    @Override
    protected void initialize() 
    {
    	
    }

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() 
    { 
    	return System.currentTimeMillis() - startTime > 15000; 
    }

    @Override
    protected void end()
    {
        driveTrainSubsystem.stop();
        shooterSubsystem.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}
