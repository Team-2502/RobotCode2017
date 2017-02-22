package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearBoxSubsystem extends Subsystem
{
    private final Solenoid moveTop;
    private final Solenoid releaseGear;
    private final Solenoid pushGear;
    private final Solenoid pushBox;

    private boolean topMoved = false;
    
    private boolean Released = false;
    
    private boolean pushedGear = false;

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

    public void switchTop() { setTop(topMoved = !topMoved); }
    
    public void setTop(boolean topMoved) { moveTop.set(this.topMoved = topMoved); }

    public void switchPushGear() { pushGear.set(pushedGear = !pushedGear); }

    public void setGear(boolean pushedGear) { pushGear.set(this.pushedGear = pushedGear); }

    public void switchRelease() { releaseGear.set(Released = !Released); }

    public void setRelease(boolean Released) { releaseGear.set(this.Released = Released); }
    
    public void switchPushBox() { pushBox.set(pushedBox = !pushedBox); }
    
    public void setBox(boolean pushedBox) { pushBox.set(this.pushedBox = pushedBox); }
}
  