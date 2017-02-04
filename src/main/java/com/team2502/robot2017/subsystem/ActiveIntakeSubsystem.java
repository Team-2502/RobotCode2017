package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ActiveCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import javafx.beans.NamedArg;
import logger.Log;
import java.io.Serializable;

/**
 * Example Implementation, Many changes needed.
 */
public class ActiveIntakeSubsystem extends Subsystem
{
    private CANTalon activeTalon; 
	
    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new ActiveCommand());
    }
    
    public ActiveIntakeSubsystem() { activeTalon = new CANTalon(RobotMap.Motor.ACTIVE_INTAKE); }

    public void activeDrive()
    {
    	if(OI.JOYSTICK_FUNCTION.getRawButton(2)) { activeTalon.set(1); }
    	else { activeTalon.set(0); }
    }

    public void stop() { activeTalon.set(0); }
}

