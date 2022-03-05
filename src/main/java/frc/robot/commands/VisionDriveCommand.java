package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/** An example command that uses an example subsystem. */
public class VisionDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final XboxController m_controller;
  private final Vision m_vision;
  public double kP = 0.05;
  public double kD = 0.015;
  public double error = 0;
  double prevError = 0;
  double deltaError = 0;
  double rotationSignal;

  JoystickButton visionDriveJoystick;

  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public VisionDriveCommand(DriveSubsystem subsystem, XboxController controller,
      Vision visionSubsystem) {
    m_drive = subsystem;
    m_controller = controller;
    m_vision = visionSubsystem;
    addRequirements(m_drive, m_vision);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vision.fetchvision();

    if (m_vision.isTargetValid()) {
      System.out.println("Target Valid!");
      double target = 0;

      if (target - m_vision.getYOffset() != error) {
        error = target - m_vision.getYOffset();
        deltaError = error - prevError;
        rotationSignal = -(kP * error + kD * deltaError);
        if (rotationSignal < -0.5) {
          rotationSignal = -0.5;
        } else if (rotationSignal > 0.5) {
          rotationSignal = 0.5;
        }
        m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
            rotationSignal * -1);
        prevError = error;

        return;
      }
      m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
          rotationSignal * -1);
      deltaError = 0;
      return;
    } else {
      m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
          0);
      System.out.println("Target Not Valid!");
    }

    error = 0;
    prevError = 0;
    deltaError = 0;
  }
}
