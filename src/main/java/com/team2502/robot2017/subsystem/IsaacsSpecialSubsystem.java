package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;	
import edu.wpi.first.wpilibj.command.Subsystem;
 
 // TODO: Isaac, Implement this however you want.
 public class IsaacsSpecialSubsystem extends Subsystem
 {
     private final Solenoid MoveTop;
     private final Solenoid lever;
     private final Solenoid pushGear;
     private final Solenoid sol3;
     
   
     boolean on = true;
     boolean off= false;
     boolean buttonPressed0 = false;
     boolean Mode0 = false;
     boolean sol0On = false;
     boolean buttonPressed1 = false;
     boolean Mode1 = false;
     boolean sol1On = false;
     boolean buttonPressed2 = false;
     boolean Mode2 = false;
     boolean sol2On = false;
     boolean buttonPressed3 = false;
     boolean Mode3 = false;
     boolean sol3On = false;
     
     public IsaacsSpecialSubsystem()
     {
         this.MoveTop = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID0);
         this.lever = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID1);
         this.pushGear = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID2);
         this.sol3 = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID3);
     }
 
     @Override
     protected void initDefaultCommand()
     {
     }
 
     public void doStuff()
     {
    	 if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(4) && !buttonPressed0)
    	 {
      		Mode0 = !Mode0;
    	 }
    	 	buttonPressed0 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(4);
    		      	
    	if(Mode0) { MoveTop.set(on); }
       	else { MoveTop.set(off); }
    	
    	if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3) && !buttonPressed1)
   	 	{
     		Mode1 = !Mode1;
   	 	}
   	 	buttonPressed1 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3);
   		      	
   	 	if(Mode1) { lever.set(on); }
      	else { lever.set(off); } 
      
   	 	if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5) && !buttonPressed2)
   	 	{
    		Mode2 = !Mode2;
   	 	}
  	 	buttonPressed2 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5);
  		      	
  	 	if(Mode2) { pushGear.set(on); }
     	else { pushGear.set(off); } 
    }
     
     public void pushGear()
     {	
    	 pushGear.set(on); 
     }
    
     public void pullGear()
     {
    	 pushGear.set(off);
     }
     
     public void leverOn()
     {
    	 lever.set(on);
     }
     
     public void leverOff()
     {
    	 lever.set(off);
     }
 }  
  