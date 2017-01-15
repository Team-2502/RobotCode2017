package com.team2502.robot2017;

import com.team2502.robot2017.chooser.TypeSendableChooser;
import com.team2502.robot2017.command.autonomous.AutonomousCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings({ "WeakerAccess" })
public final class DashboardData
{
    private DashboardData() {}

    public static TypeSendableChooser<AutonomousCommand> AUTONOMOUS_SELECTOR;
    public static TypeSendableChooser<DriveTrainSubsystem.DriveTypes> DRIVE_CONTROL_SELECTOR;

    public static void update() { updatePressure(); }

    public static void setup()
    {
        AUTONOMOUS_SELECTOR = new TypeSendableChooser<AutonomousCommand>();
        AUTONOMOUS_SELECTOR.addDefaultT("Default Auto", new AutonomousCommand());

        DRIVE_CONTROL_SELECTOR = new TypeSendableChooser<DriveTrainSubsystem.DriveTypes>();
        DRIVE_CONTROL_SELECTOR.addDefaultT("Dual Stick Drive Control", DriveTrainSubsystem.DriveTypes.DUAL_STICK);
        DRIVE_CONTROL_SELECTOR.addObjectT("Arcade Drive Control", DriveTrainSubsystem.DriveTypes.ARCADE);

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
        if(Enabler.PRESSURE.enabler[0])
        {
            if(Enabler.PRESSURE.enabler[1]) { SmartDashboard.putNumber("[0x00] Current Tank Pressure", Robot.PRESSURE_SENSOR.getPressure()); }
            if(Enabler.PRESSURE.enabler[2]) { SmartDashboard.putBoolean("[0x00] Is Compressor Enabled", Robot.COMPRESSOR.enabled()); }
            if(Enabler.PRESSURE.enabler[3]) { SmartDashboard.putBoolean("[0x00] Is Compressor Low", Robot.COMPRESSOR.getPressureSwitchValue()); }
            if(Enabler.PRESSURE.enabler[4]) { SmartDashboard.putNumber("[0x00] Current Air Compression Rate", Robot.COMPRESSOR.getCompressorCurrent()); }
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
