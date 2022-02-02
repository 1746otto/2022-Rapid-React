package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.ArcadeDriveCommand;
import edu.wpi.first.wpilibj.XboxController;

/** An example command that uses an example subsystem. */
public class AutonDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final double m_rotation;
  private final double m_speed;
  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonDriveCommand(DriveSubsystem subsystem, double rotation, double speed) { 
    m_drive = subsystem;
    m_rotation = rotation;
    m_speed = speed;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, m_rotation);
  }
}
