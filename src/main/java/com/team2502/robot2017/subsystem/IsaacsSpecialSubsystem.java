package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;	
import edu.wpi.first.wpilibj.command.Subsystem;
 
 // TODO: Isaac, Implement this however you want.
 public class IsaacsSpecialSubsystem extends Subsystem
 {
     private final Solenoid sol0;
     private final Solenoid sol1;
     private final Solenoid sol2;
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
         this.sol0 = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID0);
         this.sol1 = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID1);
         this.sol2 = new Solenoid(RobotMap.Solenoid.ISAACS_SPECIAL_SOLENOID2);
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
    		      	
    	if(Mode0) { sol0.set(on); }
       	else { sol0.set(off); }
    	
    	if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3) && !buttonPressed1)
   	 	{
     		Mode1 = !Mode1;
   	 	}
   	 	buttonPressed1 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(3);
   		      	
   	 	if(Mode1) { sol1.set(on); }
      	else { sol1.set(off); } 
      
   	 	if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5) && !buttonPressed2)
   	 	{
    		Mode2 = !Mode2;
   	 	}
  	 	buttonPressed2 = OI.JOYSTICK_DRIVE_RIGHT.getRawButton(5);
  		      	
  	 	if(Mode2) { sol2.set(on); }
     	else { sol2.set(off); } 
    }
     
 }  
  