package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ActiveCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ActiveIntakeSubsystem extends Subsystem
{
    private CANTalon activeTalon;

    public ActiveIntakeSubsystem()
    {
        activeTalon = new CANTalon(RobotMap.Motor.ACTIVE_INTAKE);
    }

    @Override
    protected void initDefaultCommand()
    {
//        this.setDefaultCommand(new ActiveCommand());
    }

    public void activeDrive(int direction)
    {
    	activeTalon.set(direction);
    	
//        if(OI.JOYSTICK_FUNCTION.getRawButton(3)) { activeTalon.set(1); }
//        else if(OI.JOYSTICK_FUNCTION.getRawButton(4)) { activeTalon.set(-1); }
//        else { activeTalon.set(0); }
    }

    public void stop()
    {
        activeTalon.set(0);
    }
}

