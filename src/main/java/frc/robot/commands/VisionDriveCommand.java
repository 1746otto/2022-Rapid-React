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
  public double kD = 0 * kP;
  public double error = 0;
  double prevError = 0;
  double deltaError = 0;

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
      // Should be in consants
      double target = 0;

      if (target - m_vision.getXOffset() != error) {
        error = target - m_vision.getXOffset();
        deltaError = error - prevError;
        double rotationSignal = -(kP * error + kD * deltaError);
        if (rotationSignal < -0.5) {
          rotationSignal = -0.5;
        } else if (rotationSignal > 0.5) {
          rotationSignal = 0.5;
        }

        m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
            rotationSignal);
        prevError = error;

        return;
      }
      deltaError = 0;
      return;
    } else {
      System.out.println("Target Not Valid!");
    }
    m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(), 0);
    error = 0;
    prevError = 0;
    deltaError = 0;


    m_drive.arcadeDrive(m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
        m_controller.getLeftX());
  }
}
