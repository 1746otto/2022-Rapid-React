package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleopDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;
  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDriveCommand(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation) { 
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble();
  }
}
