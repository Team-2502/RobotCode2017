package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ActiveCommand;
import com.team2502.robot2017.command.FeederInBoxCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Add proper implementation
public class FeederSubsystem extends Subsystem
{
    private CANTalon feederInBox;

    public FeederSubsystem()
    {
        feederInBox = new CANTalon(RobotMap.Motor.FEEDER_TALON_2);
    }

    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new FeederInBoxCommand());
    }

    public void activeDrive()
    {

        if(OI.JOYSTICK_FUNCTION.getRawButton(3)) { feederInBox.set(1); }
        else if(OI.JOYSTICK_FUNCTION.getRawButton(4)) { feederInBox.set(-1); }
        else { feederInBox.set(0); }
    }

    public void stop()
    {
        feederInBox.set(0);
    }
}

