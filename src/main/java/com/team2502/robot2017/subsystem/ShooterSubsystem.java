package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.FlywheelCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class ShooterSubsystem extends Subsystem
{

	public boolean isActiveFlywheel = false;
	public boolean isActiveFeeder = false;
	
    private final CANTalon flywheelTalon;
    private final CANTalon feederTalon1; 
    private final CANTalon feederTalon2;  
    double targetSpeed = 1670;
    double error = 0;
    
    boolean triggerPressed = false;
    boolean shooterMode = false;
    
    @Override
    protected void initDefaultCommand() 
    {
    	this.setDefaultCommand(new FlywheelCommand());
    	
    	flywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	flywheelTalon.configEncoderCodesPerRev(256);
    	flywheelTalon.reverseSensor(false);
    	
    	flywheelTalon.configNominalOutputVoltage(+0.0f, -0.0f);
    	flywheelTalon.configPeakOutputVoltage(+12.0f, -12.0f);
    	
    	flywheelTalon.setProfile(0);
    	flywheelTalon.setF(0.21765900);
    	flywheelTalon.setP(0.21312500);
    	flywheelTalon.setI(0);
    	flywheelTalon.setD(0);
    }
    
    public ShooterSubsystem()
    {
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        feederTalon1 = new CANTalon(RobotMap.Motor.FEEDER_TALON_0);
        feederTalon2 = new CANTalon(RobotMap.Motor.FEEDER_TALON_1);
    }

    // getSpeed() returns the current velocity of the flywheel.
    // This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
	public int getSpeed() { return flywheelTalon.getEncVelocity(); } 
	
	public double getMotorOutput() { return (flywheelTalon.getOutputVoltage() / flywheelTalon.getBusVoltage()); }
	
	public double getTargetSpeed() { return targetSpeed; }
	
	public double getError() { return flywheelTalon.getClosedLoopError(); }
	
	public double getTopError()
	{
		double newError = getError();
		
		if(newError > error) { error = newError; }
		
		return error;
	}
	
	public void flywheelDrive()
	{	
     	flywheelTalon.changeControlMode(TalonControlMode.Speed);
		
		// Determines if the flywheel is already active.
		// If active, turn off flywheel at button press.
		// Else, turn on flywheel at button press.
     	
     	if(OI.JOYSTICK_FUNCTION.getRawButton(5) && !triggerPressed)
     	{
     		shooterMode = !shooterMode;
     	}
     	triggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(5);
     	
     	if(shooterMode) { flywheelTalon.set(targetSpeed); }
     	else { flywheelTalon.set(0); }
     	
     	/*OLD TOGGLE MODE FOR SHOOTER WHEEL*/
//    	
//		if(OI.JOYSTICK_FUNCTION.getRawButton(5)) 
//		{
//			if(isActiveFlywheel)
//			{
//				flywheelTalon.set(0); 
//				isActiveFlywheel = false; 
//			}
//			else 
//			{
//				flywheelTalon.set(targetSpeed);
//				isActiveFlywheel = true;
//			}
//		}	
		
		if(OI.JOYSTICK_FUNCTION.getRawButton(12))
		{
			targetSpeed = (targetSpeed - 10); 
		}
		
		if(OI.JOYSTICK_FUNCTION.getRawButton(11))
		{
			targetSpeed = (targetSpeed + 10);
		}
		
		if(OI.JOYSTICK_FUNCTION.getTrigger() && (Math.abs(flywheelTalon.getEncVelocity()) > 1650))
		{	
		    feederTalon1.set(1);
		    feederTalon2.set(-1);
		}
		else
		{
			feederTalon1.set(0);
			feederTalon2.set(0);
		}
	}
	
	public void stop() {}
}

    
