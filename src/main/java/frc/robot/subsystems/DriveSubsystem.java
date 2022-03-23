package frc.robot.subsystems;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_rightLeader =
      new CANSparkMax(DriveConstants.krightDriveLeader, MotorType.kBrushless);
  private final CANSparkMax m_rightFollow =
      new CANSparkMax(DriveConstants.krightDriveFollow, MotorType.kBrushless);
  private final CANSparkMax m_leftLeader =
      new CANSparkMax(DriveConstants.kleftDriveLeader, MotorType.kBrushless);
  private final CANSparkMax m_leftFollow =
      new CANSparkMax(DriveConstants.kleftDriveFollow, MotorType.kBrushless);
  private double forwardComponent;
  private double rotationComponent;
  private double sumComponents;
  private final PigeonIMU m_pigeon = new PigeonIMU(DriveConstants.kPigeonPort);


  public DriveSubsystem() {
    m_leftLeader.setInverted(true);
    m_leftFollow.setInverted(true);

    m_rightFollow.follow(m_rightLeader);
    m_leftFollow.follow(m_leftLeader);
  }

  public void arcadeDrive(double forward, double rotation) {
    if (Math.abs(forward) < ControllerConstants.kdeadZone) {
      forward = 0;
    }
    if (Math.abs(rotation) < ControllerConstants.kdeadZone) {
      rotation = 0;
    }
    if (forward > 0 && rotation > 0) { // Quadrant 1
      if (Math.abs(forward) >= Math.abs(rotation)) {
        forwardComponent = 1;
        rotationComponent = rotation / forward;
      } else {
        forwardComponent = forward / rotation;
        rotationComponent = 1;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward > 0 && rotation < 0) { // Quadrant 2
      if (Math.abs(forward) > Math.abs(rotation)) {
        forwardComponent = 1;
        rotationComponent = rotation / forward;
      } else {
        forwardComponent = -1 * (forward / rotation);
        rotationComponent = -1;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward < 0 && rotation < 0) { // Quadrant 3
      if (Math.abs(forward) > Math.abs(rotation)) {
        forwardComponent = -1;
        rotationComponent = -1 * (rotation / forward);
      } else {
        forwardComponent = -1 * (forward / rotation);
        rotationComponent = -1;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward < 0 && rotation > 0) { // Quadrant 4
      if (Math.abs(forward) > Math.abs(rotation)) {
        forwardComponent = -1;
        rotationComponent = 1 * (rotation / forward);
      } else {
        forwardComponent = forward / rotation;
        rotationComponent = 1;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward == 0 && rotation != 0) {
      if (rotation > 0) {
        forwardComponent = 0;
        rotationComponent = 1;
      }
      if (rotation < 0) {
        forwardComponent = 0;
        rotationComponent = -1;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward != 0 && rotation == 0) {
      if (forward > 0) {
        forwardComponent = 1;
        rotationComponent = 0;
      }
      if (forward < 0) {
        forwardComponent = -1;
        rotationComponent = 0;
      }
      forwardComponent = Math.abs(forwardComponent);
      rotationComponent = Math.abs(rotationComponent);
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    }
    m_rightLeader.set(forward - rotation);
    m_leftLeader.set(forward + rotation);
  }


  public double getLeftRotations() {
    return m_leftLeader.getEncoder().getPosition();
  }

  public double getRightRotations() {
    return m_rightLeader.getEncoder().getPosition();
  }
  /*
   * public enum ParamEnum { YawOffset(160), CompassOffset(160), BetaGain(162), Reserved163(163),
   * GyroNoMotionCal(164), EnterCalibration(165), FusedHeadingOffset(166), StatusFrameRate(169),
   * AccumZ(170), TempCompDisable(171); private int value; private ParamEnum(int value) { this.value
   * = value; } }
   * 
   * private enum TareType { SetValue (0x00), AddOffset(0x01), MatchCompass(0x02), SetOffset(0xFF);
   * private int value; private Tare }
   * 
   * public int setYawToComass() { int errCode = ConfigSetParameter(ParamEnum.YawOffset,
   * Type.MatchCompass, 0); }
   */

  public void stop() {
    m_rightLeader.set(0);
    m_leftLeader.set(0);
  }

  public void generateTrajectory() {
    var sideStart =
        new Pose2d(Units.feetToMeters(0), Units.feetToMeters(0), Rotation2d.fromDegrees(-180));
    var crossScale =
        new Pose2d(Units.feetToMeters(5), Units.feetToMeters(5), Rotation2d.fromDegrees(-160));
    var interiorWaypoints = new ArrayList<Translation2d>();
    interiorWaypoints.add(new Translation2d(Units.feetToMeters(2), Units.feetToMeters(3)));
    interiorWaypoints.add(new Translation2d(Units.feetToMeters(3), Units.feetToMeters(4)));

    TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(1), Units.feetToMeters(0.5));
    config.setReversed(true);

    var trajectory =
        TrajectoryGenerator.generateTrajectory(sideStart, interiorWaypoints, crossScale, config);
  }
}
