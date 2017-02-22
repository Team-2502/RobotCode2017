package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.Command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;

public class FeederCommand extends Command {
	
	private final ShooterSubsystem shooterSubsystem;
	
	public FeederCommand()
    {
    	requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;
    }

	@Override
	protected void initialize() {}

	@Override
	protected void execute() { shooterSubsystem.driveFeeder(); }

	@Override
	protected boolean isFinished() { return false; }

	@Override
	protected void end() { shooterSubsystem.stopFeeder(); }

	@Override
	protected void interrupted() { end(); }

}
