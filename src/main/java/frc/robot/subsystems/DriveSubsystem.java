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

  public void stupidArcadeDrive(double forward, double rotation) {
    m_rightLeader.set((forward - rotation) / 2);
    m_leftLeader.set((forward + rotation) / 2);
  }

  public void stop() {
    m_rightLeader.set(0);
    m_leftLeader.set(0);
  }

}
