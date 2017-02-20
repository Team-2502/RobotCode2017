package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Isaac, Implement this however you want.
public class GearBoxSubsystem extends Subsystem
{
    private final Solenoid moveTop;
    private final Solenoid releaseGear;
    private final Solenoid pushGear;
    private final Solenoid pushBox;

    // States for debounce code. For moveTop Solenoid.
    private boolean topMoved = false;
    
    // States for debounce code. For lever Solenoid.
    private boolean Released = false;
    
    // States for debounce code. For pushGear Solenoid.
    private boolean buttonPressed2 = false;
    private boolean pushedGear = false;
    
    // States for debounce code. For slideBox Solenoid.
    private boolean buttonPressed3 = false;
    private boolean pushedBox = false;

    
    public GearBoxSubsystem()
    {
        this.moveTop = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID3);
        this.releaseGear = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID1);
        this.pushGear = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID0);
        this.pushBox = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID2);
    }

    @Override
    protected void initDefaultCommand() {}

    public void switchTop()
    {
    	setTop(topMoved = !topMoved);
    }
    
    public void setTop(boolean topMoved)
    {
    	moveTop.set(this.topMoved = topMoved);
    }
    
    public void doStuff()
    {
    	
    	
//        if(OI.JOYSTICK_FUNCTION.getRawButton(12))
//        {
//        	moveTop.set(RobotMap.Solenoid.GEARBOX_SOLENOID0 = !);
//        }

//    	switchTop();
//    	
//        // If button 3 on right joystick, toggles solenoid
//        if(OI.JOYSTICK_FUNCTION.getRawButton(9) && !buttonPressed1)
//        {
//            mode1 = !mode1;
//        }
//        buttonPressed1 = OI.JOYSTICK_FUNCTION.getRawButton(3);
//
//        releaseGear.set(mode1);
//
//        // If button 5 on right joystick, toggles solenoid
//        if(OI.JOYSTICK_FUNCTION.getRawButton(11) && !buttonPressed2)
//        {
//            mode2 = !mode2;
//        }
//        buttonPressed2 = OI.JOYSTICK_FUNCTION.getRawButton(5);
//
//        pushGear.set(mode2);
//        
//        // If button 2 on right joystick, toggles solenoid
//        if(OI.JOYSTICK_FUNCTION.getRawButton(7) && !buttonPressed3)
//        {
//            mode3 = !mode3;
//        }
//        buttonPressed2 = OI.JOYSTICK_FUNCTION.getRawButton(2);
//
//        pushBox.set(mode3);
   }

    public void switchPushGear()
    {
        pushGear.set(pushedGear = !pushedGear);
    }

//    public void pullGear()
//    {
//        pushGear.set(false);
//    }

    public void setGear(boolean pushedGear)
    {
        pushGear.set(this.pushedGear = pushedGear);
    }

    public void switchRelease()
    {
        releaseGear.set(Released = !Released);
    }

//    public void leverOff()
//    {
//        releaseGear.set(false);
//    }

    public void setRelease(boolean Released)
    {
        releaseGear.set(this.Released = Released);
    }
    
    public void switchPushBox()
    {
    	pushBox.set(pushedBox = !pushedBox);
    }
    
    public void setBox(boolean pushedBox)
    {
    	pushBox.set(this.pushedBox = pushedBox);
    }
}
  