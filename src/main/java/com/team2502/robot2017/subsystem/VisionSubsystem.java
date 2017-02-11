package com.team2502.robot2017.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


	
// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem implements ITableListener
{	
	NetworkTable visionTable; 
	
    double offset;
    double width;
    double height;
   
    @Override
    public void initDefaultCommand()
    {	
    	NetworkTable.setServerMode();
    	NetworkTable.shutdown();
    	visionTable = NetworkTable.getTable("vision");
    	offset = visionTable.getNumber("offset", 1023.0);
    	width = visionTable.getNumber("dimensions-px-x", 1023.0);
    	height = visionTable.getNumber("dimensions-px-y", 1023.0);
    	visionTable.addTableListener(this);
//    	offset = 1.0;
//    	width = 2.0;
//    	height = 3.0;
    }
    
    public double getOffset(){ return offset; }
    public double getWidth(){ return width; }
    public double getHeight(){ return height; }

	@Override
	public void valueChanged(ITable table, String key, Object value, boolean isNewKey) {
		// This runs whenever the value in networktables changes
		if(key == "offset"){
			
			offset = (double) value;
			
		} else if(key == "dimensions-px-x"){
			
			width = (double) value;
			
		} else if(key == "dimensions-px-y"){
			
			height = (double) value;
			
		}
		
	}
}
