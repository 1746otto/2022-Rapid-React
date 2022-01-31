// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static class CANIDConstants {
        public static final int kleftDriveLeader = 20; 
        public static final int kleftDriveFollow = 21;
        public static final int krightDriveLeader = 10;
        public static final int krightDriveFollow = 11;
    }

    public static class AutonConstants {
        public static final double kautonVelocity = 0.3;
        public static final double kautonDriveTime = 2.0;
    }
    
    public static class ControllerConstants {
        public static final int kport = 0;
        public static final double kdeadZone = .125;
        public static final double kDriveControl = 2.0;
    }
    
}
