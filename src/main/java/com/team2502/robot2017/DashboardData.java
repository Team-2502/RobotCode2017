package com.team2502.robot2017;

import com.team2502.robot2017.chooser.TypeSendableChooser;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.command.autonomous.AutoCommandG1;
import com.team2502.robot2017.command.autonomous.AutoCommandG2;
import com.team2502.robot2017.command.autonomous.AutoCommandG3;
import com.team2502.robot2017.command.autonomous.AutoCommandG4;
import com.team2502.robot2017.command.autonomous.AutoCommandG5;
import com.team2502.robot2017.command.autonomous.AutonomousCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import logger.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings({ "WeakerAccess" })
public final class DashboardData
{

    public static final TypeSendableChooser<Command> AUTONOMOUS_SELECTOR = new TypeSendableChooser<Command>();    
    public static final TypeSendableChooser<DriveTrainSubsystem.DriveTypes> DRIVE_CONTROL_SELECTOR = new TypeSendableChooser<DriveTrainSubsystem.DriveTypes>();
    public static String VERSION = "Unknown Version";
    
    private DashboardData() {}

    public static void update()
    {
        updatePressure();
        updateNavX();
        updateSelectors();
        
        
        SmartDashboard.putString("Version", VERSION);
    }

    public static void setup()
    {
        AUTONOMOUS_SELECTOR.addObjectT("FULLBUTDOESNTWORK", new AutoCommandG1());
        AUTONOMOUS_SELECTOR.addDefaultT("TESTING", new AutoCommandG2());
        AUTONOMOUS_SELECTOR.addObjectT("Group1-StartCloserToBoiler", new AutoCommandG3());
        AUTONOMOUS_SELECTOR.addObjectT("Group2-StartMiddle", new AutoCommandG4());
        AUTONOMOUS_SELECTOR.addObjectT("Group3-StartAtFarEnd", new AutoCommandG5());
        

        DRIVE_CONTROL_SELECTOR.addDefaultT("Dual Stick Drive Control", DriveTrainSubsystem.DriveTypes.DUAL_STICK);
        DRIVE_CONTROL_SELECTOR.addObjectT("Arcade Drive Control", DriveTrainSubsystem.DriveTypes.ARCADE);

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(DashboardData.class.getResourceAsStream("/version.properties")));
            String line;
            while((line = br.readLine()) != null)
            {
                if(line.startsWith("version="))
                {
                    String[] split = line.split("=");
                    if((split.length < 2) || (split[1] == null) || split[1].isEmpty()) { throw new Exception(); }
                    VERSION = split[1];
                    break;
                }
            }
            br.close();
        } catch(Exception e) { Log.error("Could not get version."); }
    }

    public static Command getAutonomous()
    {
        return AUTONOMOUS_SELECTOR.getSelectedT();
    }

    public static DriveTrainSubsystem.DriveTypes getDriveType()
    {
        return DRIVE_CONTROL_SELECTOR.getSelectedT();
    }
    
    private static void updateNavX()
    {
    	SmartDashboard.putNumber("NavX: Yaw", Robot.NAVX.getYaw());
    	SmartDashboard.putNumber("NavX: Roll", Robot.NAVX.getRoll());
    	SmartDashboard.putNumber("NavX: Pitch", Robot.NAVX.getPitch());
    	SmartDashboard.putNumber("NavX: Angle", Robot.NAVX.getAngle());
    }

    private static void updatePressure()
    {
    	
        SmartDashboard.putNumber("FW: Current Flywheel Speed", Robot.SHOOTER.getSpeed());
        SmartDashboard.putNumber("FW: Target Speed", Robot.SHOOTER.getTargetSpeed());
        SmartDashboard.putNumber("FW: Loop Error", Robot.SHOOTER.getError());
        SmartDashboard.putNumber("FW: Motor Output", Robot.SHOOTER.getMotorOutput());
        
        SmartDashboard.putNumber("NavX: Pitch", Robot.NAVX.getPitch());
        SmartDashboard.putNumber("NavX: Roll", Robot.NAVX.getRoll());
        SmartDashboard.putNumber("NavX: Yaw", Robot.NAVX.getYaw());
        SmartDashboard.putNumber("NavX: Raw Accel X", Robot.NAVX.getRawAccelX());


        SmartDashboard.putNumber ("DS:Current Distance (in)", (double) Robot.DISTANCE_SENSOR.getSensorDistance());

        if(Enabler.PRESSURE.enabler[0])
        {
            if(Enabler.PRESSURE.enabler[1]) { SmartDashboard.putNumber("Current Tank Pressure", Robot.PRESSURE_SENSOR.getPressure()); }
            if(Enabler.PRESSURE.enabler[2]) { SmartDashboard.putBoolean("Is Compressor Enabled", Robot.COMPRESSOR.enabled()); }
            if(Enabler.PRESSURE.enabler[3]) { SmartDashboard.putBoolean("Is Compressor Low", Robot.COMPRESSOR.getPressureSwitchValue()); }
            if(Enabler.PRESSURE.enabler[4]) { SmartDashboard.putNumber("Current Air Compression Rate", Robot.COMPRESSOR.getCompressorCurrent()); }


        }

    }
    
    public static void updateSelectors()
    {
        if(Enabler.AUTONOMOUS.enabler[0])
        {
            SmartDashboard.putData("Auto Mode", AUTONOMOUS_SELECTOR);
        }

        if(Enabler.DRIVE_CONTROL.enabler[0])
        {
            SmartDashboard.putData("Drive Control Mode", DRIVE_CONTROL_SELECTOR);
        }
    }

    private enum Enabler
    {
        AUTONOMOUS(true),
        PRESSURE(true, true, true, false, true),
        DRIVE_CONTROL(true);

        public final boolean[] enabler;

        Enabler(boolean... enabler) { this.enabler = enabler; }
    }
}
