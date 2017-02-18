package com.team2502.robot2017.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionSubsystem extends Subsystem
{
    private final NetworkTable table;

    public VisionSubsystem()
    {
        table = NetworkTable.getTable("PiVision");
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public DriveTrainSubsystem.Pair<byte[], byte[]> getContours()
    {
        byte[] contours0 = new byte[1000];
        byte[] contours1 = new byte[1000];
        contours0 = table.getRaw("contours0", contours0);
        contours1 = table.getRaw("contours1", contours1);
        return new DriveTrainSubsystem.Pair<byte[], byte[]>(contours0, contours1);
    }

    public void processData()
    {
        // TODO: Add Implementation
    }
}
