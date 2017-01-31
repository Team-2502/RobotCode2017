package com.team2502.robot2017;

@SuppressWarnings({ "WeakerAccess" })
public class RobotMap
{
    private RobotMap() {}

    public static final class Joystick
    {
        private Joystick() {}

        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_FUNCTION = 2;

        public static final class Button {}
    }

    public static final class Electrical
    {
        private Electrical() {}

        public static final int PRESSURE_SENSOR = 0;
        public static final double ENC = 0;
    }

    public static final class Motor
    {
    	
        private Motor() {}

        public static final int LEFT_TALON_0 = 2;
        public static final int LEFT_TALON_1 = 4;
        public static final int RIGHT_TALON_0 = 1;
        public static final int RIGHT_TALON_1 = 3;
        public static final int FLYWHEEL_TALON_0 = 5;
        public static final int FEEDER_TALON_0 = 6; 
    }
}
