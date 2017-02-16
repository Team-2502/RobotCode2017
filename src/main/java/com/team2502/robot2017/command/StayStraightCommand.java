package com.team2502.robot2017.command;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class StayStraightCommand extends Command{

	public double yaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	
	public StayStraightCommand() {
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        
		yaw = navx.getYaw();
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double currentYaw = Robot.NAVX.getYaw();
		if(currentYaw > yaw){
			driveTrain.runMotors(driveTrain.leftSpeed*(currentYaw-yaw), driveTrain.rightSpeed*0);
		} else if(currentYaw < yaw){
			driveTrain.runMotors(driveTrain.leftSpeed*0, driveTrain.rightSpeed*(yaw-currentYaw));
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
