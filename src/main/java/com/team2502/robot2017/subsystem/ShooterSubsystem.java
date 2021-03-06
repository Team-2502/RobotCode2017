package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ShooterChangeSpeedCommand;
import com.team2502.robot2017.command.ShooterCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{
    private final CANTalon flywheelTalon;
    private final CANTalon feederTalon0; //coleson
    private final CANTalon feederTalon1;  //banebot
    public final CANTalon feederTalon2; //agitator
    
    double targetSpeed = 1670;
    int error = 0;
    
    public boolean isFlywheelActive;
    public boolean isFeederActive;
    private boolean shooterMode = false;
    private boolean isTriggerPressed = false;
    
    public ShooterSubsystem()
    {
    	 flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
         feederTalon0 = new CANTalon(RobotMap.Motor.FEEDER_TALON_0);
         feederTalon1 = new CANTalon(RobotMap.Motor.FEEDER_TALON_1);
         feederTalon2 = new CANTalon(RobotMap.Motor.FEEDER_TALON_2);
    }
    
    @Override
    protected void initDefaultCommand()
    {
    	this.setDefaultCommand(new ShooterCommand(1));
    	
    	flywheelTalon.changeControlMode(TalonControlMode.Speed);
    	
    	flywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	flywheelTalon.configEncoderCodesPerRev(256);
    	flywheelTalon.reverseSensor(false);
    	
    	flywheelTalon.configNominalOutputVoltage(0.0D, -0.0D);
    	flywheelTalon.configPeakOutputVoltage(12.0D, -12.0D);
    	
    	flywheelTalon.setProfile(0);
    	flywheelTalon.setF(0.21765900);
    	flywheelTalon.setP(1.71312500);
    	flywheelTalon.setI(0.0);
    	flywheelTalon.setD(0.0);
    }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the flywheel.
     */
    public int getSpeed()
    {
        return flywheelTalon.getEncVelocity();
    }

    public double getMotorOutput()
    {
        return flywheelTalon.getOutputVoltage() / flywheelTalon.getBusVoltage();
    }

    public double getTargetSpeed()
    {
        return targetSpeed;
    }

    public int getError()
    {
        return flywheelTalon.getClosedLoopError();
    }

    public int getTopError()
    {
        int newError = getError();

        if(newError > error) { error = newError; }

        return error;
    }
    
    public void setTargetSpeed(int speedChange)
    {
    	targetSpeed += speedChange;
    }
    
    public void toggleFlywheel()
    {
    	shooterMode = !shooterMode;
    	
    	if(shooterMode) { flywheelTalon.set(targetSpeed); }
    	else { flywheelTalon.set(0); }
    }
    
    public void onlySpinAgitator()
    {
    	feederTalon2.set(1);
    }
    
    public void driveFeeder()
    {
        feederTalon0.set(1);
        feederTalon1.set(-1);
        feederTalon2.set(1);
    }
    
    public void stopFeeder()
    {
    	feederTalon0.set(0);
        feederTalon1.set(0);
        feederTalon2.set(0);
    }
	
	public void flywheelDrive()
	{	
     	
     	// Toggle mode for flywheel. It is bound to button 5 on the Function stick.
//     	if(OI.JOYSTICK_FUNCTION.getRawButton(5) && !isTriggerPressed)
//     	{
//     		shooterMode = !shooterMode;
//     	}
//     	isTriggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(5);
//     	
//     	if(shooterMode) { flywheelTalon.set(targetSpeed); }
//     	else { flywheelTalon.set(0); }

        // For changing the flywheel speed.
//        if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(3))
//        {
//            targetSpeed += 10;
//        }
//        else if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(2))
//        {
//            targetSpeed -= 10;
//        }


        //Control for turning on/off the feeding mechanism.
		if(OI.JOYSTICK_FUNCTION.getTrigger() /*&& (Math.abs(flywheelTalon.getEncVelocity()) > Math.abs(targetSpeed - 500))*/)
		{	
		    feederTalon0.set(1);
		    feederTalon1.set(-1);
		    feederTalon2.set(1);
		}
//		else if(OI.JOYSTICK_FUNCTION.getTrigger() /*&& (Math.abs(flywheelTalon.getEncVelocity()) < Math.abs(targetSpeed - 500))*/)
//		{
//			feederTalon0.set(1);
//			feederTalon1.set(0);
//		}
		else
		{
			feederTalon0.set(0);
			feederTalon1.set(0);
			feederTalon2.set(0);
		}
    }

    public void stop()
    {
        flywheelTalon.set(0.0D);
        feederTalon0.set(0.0D);
        feederTalon1.set(0.0D);
        feederTalon2.set(0.0D);

        isFlywheelActive = false;
        isFeederActive = false;
    }
}

    
