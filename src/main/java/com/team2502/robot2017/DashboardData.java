package com.team2502.robot2017;

import com.team2502.robot2017.chooser.TypeSendableChooser;
import com.team2502.robot2017.command.autonomous.AutonomousCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings({ "WeakerAccess" })
public final class DashboardData
{
    public static final TypeSendableChooser<AutonomousCommand> AUTONOMOUS_SELECTOR = new TypeSendableChooser<AutonomousCommand>();
    public static final TypeSendableChooser<DriveTrainSubsystem.DriveTypes> DRIVE_CONTROL_SELECTOR = new TypeSendableChooser<DriveTrainSubsystem.DriveTypes>();

    private DashboardData() {}

    public static void update()
    {
        updatePressure();
    }

    public static void setup()
    {
        AUTONOMOUS_SELECTOR.addDefaultT("Default Auto", new AutonomousCommand());

        DRIVE_CONTROL_SELECTOR.addDefaultT("Arcade Drive Control", DriveTrainSubsystem.DriveTypes.ARCADE);
        DRIVE_CONTROL_SELECTOR.addObjectT("Dual Stick Drive Control", DriveTrainSubsystem.DriveTypes.DUAL_STICK);

        if(Enabler.AUTONOMOUS.enabler[0])
        {
            SmartDashboard.putData("Auto Mode", AUTONOMOUS_SELECTOR);
        }

        if(Enabler.DRIVE_CONTROL.enabler[0])
        {
            SmartDashboard.putData("Drive Control Mode", DRIVE_CONTROL_SELECTOR);
        }
    }

    public static AutonomousCommand getAutonomous()
    {
        return AUTONOMOUS_SELECTOR.getSelectedT();
    }

    public static DriveTrainSubsystem.DriveTypes getDriveType()
    {
        return DRIVE_CONTROL_SELECTOR.getSelectedT();
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


        SmartDashboard.putNumber("Current Dist Sensor Voltage", Robot.DISTANCE_SENSOR.getSensorVoltage());
      

        if(Enabler.PRESSURE.enabler[0])
        {
            if(Enabler.PRESSURE.enabler[1]) { SmartDashboard.putNumber("Current Tank Pressure", Robot.PRESSURE_SENSOR.getPressure()); }
            if(Enabler.PRESSURE.enabler[2]) { SmartDashboard.putBoolean("Is Compressor Enabled", Robot.COMPRESSOR.enabled()); }
            if(Enabler.PRESSURE.enabler[3]) { SmartDashboard.putBoolean("Is Compressor Low", Robot.COMPRESSOR.getPressureSwitchValue()); }
            if(Enabler.PRESSURE.enabler[4]) { SmartDashboard.putNumber("Current Air Compression Rate", Robot.COMPRESSOR.getCompressorCurrent()); }
            
            
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
