package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraightPIDCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final PigeonIMU m_pigeon;
  private final double m_distance; // rotations
  private final double m_speed;
  private double m_startingRotations;
  private double m_error;
  private double m_target;
  private double m_prevError;
  private double m_kP = 1 / 180;
  private double m_kD;
  private double m_rotationSignal;
  private double m_deltaError;

  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightPIDCommand(DriveSubsystem driveSubsystem, PigeonIMU pigeon,
      double distance /* feet */, double speed /* always positive */) {
    m_drive = driveSubsystem;
    m_pigeon = pigeon;
    m_distance = distance * 12 / Constants.DriveConstants.kwheelCircumfrence
        * Constants.DriveConstants.kmotorToWheelRatio; // feet
    m_speed = (distance >= 0 ? 1 : -1) * Math.abs(speed);

    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void initialize() {
    m_drive.arcadeDrive(m_speed, 0);
    // yaw returns a value in degrees and does not overflow until 23040.
    m_pigeon.setYaw(0);
    m_startingRotations = m_drive.getLeftRotations();
    System.out.println("StartRot" + m_startingRotations);
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);

    m_error = m_target - m_pigeon.getYaw();
    m_deltaError = m_error - m_prevError;
    m_rotationSignal = -(m_kP * m_error + m_kD * m_deltaError);
    m_drive.arcadeDrive(m_speed, m_rotationSignal * -1);
    m_prevError = m_error;

    System.out.println("Left Ticks: " + m_drive.getLeftRotations());
    System.out.println("Right Ticks: " + m_drive.getRightRotations());
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (m_distance < 0) {
      if (m_distance >= (m_drive.getLeftRotations() - m_startingRotations)) {
        return true;
      }
    } else {
      if (m_distance <= (m_drive.getLeftRotations() - m_startingRotations)) {
        return true;
      }
    }
    return false;
  }
}
