// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static class DriveConstants {
        public static final int FrontLeftID = 4;
        public static final int BackLeftID = 6;
        public static final int FrontRightID = 5;
        public static final int BackRightID = 7;
        public static final int CurrentLimit = 40;
    }

    public final class ShooterConstants {
        public static final int hubL = 3;
        public static final int hubR = 2;
        public static final int rightSensor = 0;
        public static final int leftSensor = 1;
        public static final double shortFire = 80.0;
        public static final double medFire = 100.0;
        public static final double longFire = 120.0;
        // public static final int rightCompRelay = ;
    }

    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
        public static final int shortButton = XboxController.Button.kA.value;
        public static final int medButton = XboxController.Button.kB.value;
        public static final int longButton = XboxController.Button.kY.value;
        public static final int fireButton = XboxController.Button.kX.value;
        public static final int safeFireButton = XboxController.Button.kLeftBumper.value;
        public static final int shortPressure = 50;
        public static final int medPressure = 75;
        public static final int bigPressure = 100;
   }
}
