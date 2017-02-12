package com.team2502.robot2017.command.autonomous;

//import com.team2502.robot2017.command.DriveTimeCommand;
//import com.team2502.robot2017.command.StopDrivingCommand;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import com.team2502.robot2017.command.autonomous.VisionAuto;


public class AutoCommandG extends CommandGroup
{
    public AutoCommandG()
    {
        addSequential(new VisionAuto());
        addSequential(new GearCommandGON());
        addSequential(new GearCommandGOFF());

    }
}
