package com.team2502.robot2017.chooser;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.jetbrains.annotations.Nullable;

public class TypeSendableChooser<T> extends SendableChooser
{
    public void addObjectT(String name, T object)
    {
        this.addObject(name, object);
    }

    public void addDefaultT(String name, T object)
    {
        this.addDefault(name, object);
    }

    @Nullable
    public T getSelectedT()
    {
        return (T) getSelected();
    }
}
