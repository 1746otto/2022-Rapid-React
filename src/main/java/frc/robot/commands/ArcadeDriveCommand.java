package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.ArcadeDriveCommand;
import edu.wpi.first.wpilibj.XboxController;

/** An example command that uses an example subsystem. */
public class ArcadeDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final XboxController m_controller;
  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDriveCommand(DriveSubsystem subsystem, XboxController controller) { 
    m_drive = subsystem;
    m_controller = controller;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_drive.arcadeDrive(m_controller.getLeftY(), m_controller.getLeftX());
  }
}
