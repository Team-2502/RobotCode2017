package com.team2502.robot2017.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


	
// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem
{	
	static NetworkTable visionTable; 
	
    double offset;
    double width;
    double height;
   
    @Override
    public void initDefaultCommand()
    {	
    	NetworkTable.setServerMode();
    	NetworkTable.shutdown();
    	visionTable = NetworkTable.getTable("vision");
    	
    }
    
    public static double getOffset(){ return visionTable.getNumber("offset", 1023.0); }
    public static double getWidth(){ return visionTable.getNumber("dimensions-px-x", 1023.0); }
    public static double getHeight(){ return visionTable.getNumber("dimensions-px-y", 1023.0); }

	
}
