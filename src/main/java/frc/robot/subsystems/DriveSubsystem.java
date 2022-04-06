package frc.robot.subsystems;

import java.lang.reflect.Type;
import com.ctre.phoenix.sensors.PigeonIMU;
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
  private final PigeonIMU m_pigeon = new PigeonIMU(6);

  public DriveSubsystem() {
    m_leftLeader.setInverted(true);
    m_leftFollow.setInverted(true);

    m_rightFollow.follow(m_rightLeader);
    m_leftFollow.follow(m_leftLeader);
  }

  public void arcadeDrive(double forward, double rotation) {
    // squaring inputs


    forward *= Math.abs(forward);
    rotation *= Math.abs(rotation);



    if (Math.abs(forward) < ControllerConstants.kdeadZone) {
      forward = 0;
    }
    if (Math.abs(rotation) < ControllerConstants.kdeadZone) {
      rotation = 0;
    }

    // if (rotation != 0 && forward != 0) {
    // if (forward < 0.5) {
    // if (rotation > 0) {
    // rotation = Math.max(rotation + DriveConstants.kProportionalConstant * forward, 1);
    // } else {
    // rotation = Math.min(rotation - DriveConstants.kProportionalConstant * forward, -1);
    // }
    // } else {
    // if (rotation > 0) {
    // rotation = Math.min(rotation - DriveConstants.kProportionalConstant * forward, 0);
    // } else {
    // rotation = Math.max(rotation + DriveConstants.kProportionalConstant * forward, 0);
    // }
    // }
    // }

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

    // if (Math.abs(forward) > 0.8) {
    // rotation /= 2;
    // }

    m_rightLeader.set(forward - rotation);
    m_leftLeader.set(forward + rotation);

    System.out.println("forward is " + (forward - rotation));
    System.out.println("rotation is " + (rotation + rotation));
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
  public void stupidArcadeDrive(double forward, double rotation) {
    m_rightLeader.set((forward - rotation) / 2);
    m_leftLeader.set((forward + rotation) / 2);
  }

  public void setr(double forward) {
    m_rightLeader.set(forward);
  }

  public void setl(double forward) {
    m_leftLeader.set(forward);
  }


  @Override
  public void periodic() {
    System.out.println("Yaw is " + m_pigeon.getYaw() % 360);
    System.out.println("fused heading is " + m_pigeon.getFusedHeading() % 360);
    System.out.println("pitch is " + m_pigeon.getPitch());
    System.out.println("roll is " + m_pigeon.getRoll());
  }

  public void stop() {
    m_rightLeader.set(0);
    m_leftLeader.set(0);
  }

}
