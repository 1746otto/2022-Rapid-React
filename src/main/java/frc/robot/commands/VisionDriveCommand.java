package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants.autoAimConstants;

/** An example command that uses an example subsystem. */
public class VisionDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final XboxController m_controller;
  private final Vision m_vision;
  public double kP = 0.05;
  public double kD = 0 * kP;
  JoystickButton visionDriveJoystick;

  /*
   * Creates a new TeleopDriveCommand.
   * 
   * @param subsystem The subsystem used by this command.
   */
  public VisionDriveCommand(DriveSubsystem driveSubsystem, XboxController controller,
      Vision visionSubsystem) {
    m_drive = driveSubsystem;
    m_controller = controller;
    m_vision = visionSubsystem;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vision.fetchvision();

    if (m_vision.isTargetValid()) {
      System.out.println("Target Valid!");

      autoAimConstants.kError = autoAimConstants.kTarget - m_vision.getXOffset();
      autoAimConstants.kDeltaError = autoAimConstants.kError - autoAimConstants.kPrevError;
      double rotationSignal = -(kP * autoAimConstants.kError + kD * autoAimConstants.kDeltaError);

      if (rotationSignal < -0.5) {
        rotationSignal = -0.5;
      } else if (rotationSignal > 0.5) {
        rotationSignal = 0.5;
      }

      m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
          rotationSignal);
      autoAimConstants.kPrevError = autoAimConstants.kError;
      return;
    } else {
      System.out.println("Target Not Valid!");
    }
    m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(), 0);
    autoAimConstants.kError = 0;
    autoAimConstants.kPrevError = 0;
    autoAimConstants.kDeltaError = 0;
  }
}
