package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    } else if (forward < 0 && rotation > 0) { // Quadrant 4
      if (Math.abs(forward) > Math.abs(rotation)) {
        forwardComponent = -1;
        rotationComponent = -1 * (rotation / forward);
      } else {
        forwardComponent = forward / rotation;
        rotationComponent = 1;
      }
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
      sumComponents = forwardComponent + rotationComponent;
      forward /= sumComponents;
      rotation /= sumComponents;
    }
    m_rightLeader.set(forward - rotation);
    m_leftLeader.set(forward + rotation);
  }

  public void stop() {
    m_rightLeader.set(0);
    m_leftLeader.set(0);
  }

}
