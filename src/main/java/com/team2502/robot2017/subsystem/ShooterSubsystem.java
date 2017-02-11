package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.FlywheelCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{
    private final CANTalon flywheelTalon;
    private final CANTalon feederTalon0;
    private final CANTalon feederTalon1;

    public boolean isFlywheelActive;
    public boolean isFeederActive;
    private boolean isTriggerPressed;
    private boolean shooterMode;

    private double targetSpeed;
    private int error;


    public ShooterSubsystem()
    {
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        feederTalon0 = new CANTalon(RobotMap.Motor.FEEDER_TALON_0);
        feederTalon1 = new CANTalon(RobotMap.Motor.FEEDER_TALON_1);

        isFlywheelActive = false;
        isFeederActive = false;
        isTriggerPressed = false;
        shooterMode = false;

        targetSpeed = 1670.0D;
        error = 0;
    }

    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new FlywheelCommand());

        flywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        flywheelTalon.configEncoderCodesPerRev(256);
        flywheelTalon.reverseSensor(false);

        flywheelTalon.configNominalOutputVoltage(0.0D, -0.0D);
        flywheelTalon.configPeakOutputVoltage(12.0D, -12.0D);

        flywheelTalon.setProfile(0);
        flywheelTalon.setF(0.217659D);
        flywheelTalon.setP(0.213125D);
        flywheelTalon.setI(0.0D);
        flywheelTalon.setD(0.0D);
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

    public void flywheelDrive()
    {
        flywheelTalon.changeControlMode(TalonControlMode.Speed);

        /*
         Determines if the flywheel is already active.
         If active, turn off flywheel at button press.
         Else, turn on flywheel at button press.
        */
        if(OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.SHOOTER_TOGGLE) && !isTriggerPressed)
        {
            shooterMode = !shooterMode;
        }
        isTriggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.SHOOTER_TOGGLE);

        if(shooterMode) { flywheelTalon.set(targetSpeed); }
        else { flywheelTalon.set(0); }

     	/* OLD TOGGLE MODE FOR SHOOTER WHEEL */
//		if(OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.SHOOTER_TOGGLE))
//		{
//			if(isFlywheelActive)
//			{
//				flywheelTalon.set(0); 
//				isFlywheelActive = false;
//			}
//			else 
//			{
//				flywheelTalon.set(targetSpeed);
//				isFlywheelActive = true;
//			}
//		}

        if(OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.SHOOTER_INCREASE_SPEED))
        {
            targetSpeed += 10;
        }
        else if(OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.SHOOTER_DECREASE_SPEED))
        {
            targetSpeed -= 10;
        }


        if(OI.JOYSTICK_FUNCTION.getTrigger() && Math.abs(flywheelTalon.getEncVelocity()) > 1650.0D)
        {
            feederTalon0.set(1.0D);
            feederTalon1.set(-1.0D);
        }
        else
        {
            feederTalon0.set(0.0D);
            feederTalon1.set(0.0D);
        }
    }

    public void stop()
    {
        flywheelTalon.set(0.0D);
        feederTalon0.set(0.0D);
        feederTalon1.set(0.0D);

        isFlywheelActive = false;
        isFeederActive = false;
    }
}

    
