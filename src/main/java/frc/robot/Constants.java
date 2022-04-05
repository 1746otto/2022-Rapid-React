// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of@
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class ControllerConstants {
    public static final int kport = 0;
    public static final int kport2 = 1;
    public static final double kdeadZone = .125;
    public static final double kDriveControl = 2.0;
  }

  public static class RobotConstants {
    public static final int kREVPH = 2;
  }

  public static class DriveConstants {
    public static final int kleftDriveLeader = 12;
    public static final int kleftDriveFollow = 13;
    public static final int krightDriveLeader = 10;
    public static final int krightDriveFollow = 11;
    public static final int kpigeon = 6;
    public static final double kwheelCircumfrence = 4 * Math.PI;
    public static final double kmotorToWheelRatio = 68 / 12;
    public static final double kSafeTurnSpeed = 0.3;
    public static final double kProportionalConstant = 0.05;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotor = 20;
    public static final int kIntakeOmniWheels = 21;
    public static final int kIntakeSolenoid = 11;
    public static final int kIntakeFullPower = 1;
    public static final double kIntakerunZeroPower = 0.0;
    public static final double kIntakeCustomPower = 0.83;
    public static final boolean kIntakeExtended = true;
    public static final boolean kIntakeRetracted = false;
  }

  public static class ClimberConstants {
    public static final int kMotorR = 51;
    public static final int kMotorL = 50;
    public static final double kClimberExtendSpeed = 0.7;
    public static final double kClimberRetractSpeed = -0.7;
    public static final int kTopLimitSwitch = 1;
    public static final int kBottomLimitSwitch = 0;
    public static final int kChannel = 2;
    public static final boolean kClimberHookEngaged = false;
    public static final boolean kClimberHookReleased = true;
    public static final int kExtendSolenoidChannel = 12;
    public static final int kRetractSolenoidChannel = 13;
  }

  public static class AutonConstants {
    public static final double kautonVelocity = 0.6;
    public static final double kautonDriveTime = 1.5;
    public static final double kautonSpeedBackwards = 0.7;
    public static double kSpeedUpTime = 2;
    public static double kShootTime = 4.0;
    public static double kDistanceToBall = 25.0;
  }

  public static class ShooterConstants {
    public static final int kShooterMaster = 40;
    public static final int kShooterSlave1 = 41;
    public static final int kShooterSlave2 = 43;
    public static final int kShooterSlave3 = 42;
    public static final int kIndexerMotor = 30;
    public static final double kFullPower = 0.7;
    public static final int kZeroPower = 0;
    public static final double kLowGoalSpeed = 0.3;
    public static final double kRPMToTPS = 4096 / 600;
    public static final double kTPSToRPM = 1 / kRPMToTPS;
    public static final double kHighGoalRPM = 1000;
    public static final double kIndexerWindowHigh = 2000;
    public static final double kSetPointRPM = 1925;
    public static final double kIndexerWindowLow = 1850;
    public static final double kLGHighRPM = 1100;
    public static final double kLGLowRPM = 1000;
    public static final double kFullPowerLow = 0.45;
    public static final double kLowPowerLow = 0.2;
  }



  public static class IndexerConstants {
    public static final double kLowerFullForward = 0.5;
    public static final double kUpperFullForward = 0.6;
    public static final double kLowerHalfForward = 0.5;
    public static final double kUpperHalfForward = 0.5;
    public static final double kLowerStop = 0.0;
    public static final double kUpperStop = 0.0;
    public static final int kIndexer = 30;
    public static final int kLower = 30;
    public static final int kUpper = 31;
    public static final int kBeamBreakTop = 0;
    public static final int kBeamBreakBottom = 1;
    public static final double kLowerCustomPower = 0.5;
    public static final double kUpperCustomPower = 0.5;
    public static final double kTwoBallDelay = 1.3;
  }



  public static class ShooterHoodConstants {
    // Test these constants later. Then delete comment.
    public static final boolean kShooterHoodExtended = true;
    public static final boolean kShooterHoodRetracted = false;
    public static final int kShooterHood = 14;
  }
}
