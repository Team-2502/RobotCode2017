package com.team2502.robot2017;

@SuppressWarnings({ "WeakerAccess" })
public class RobotMap
{
    private RobotMap() {}

    public static final class Joystick
    {
        private Joystick() {}

        public static final int JOYSTICK_DRIVE_LEFT = 0;
        public static final int JOYSTICK_DRIVE_RIGHT = 1;
        public static final int JOYSTICK_FUNCTION = 2;

        public static final class Button {}
    }

    public static final class Electrical
    {
        private Electrical() {}

        public static final int PRESSURE_SENSOR = 0;
    }
}
