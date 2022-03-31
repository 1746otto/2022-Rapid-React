package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterFullPowerCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public final ShooterSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterFullPowerCommand(ShooterSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setFullPowerHigh();
  }

  @Override
  public void execute() {
    if (m_subsystem.getRPM() < 1600) {
      m_subsystem.setFullPowerHigh();
    } else if (m_subsystem.getRPM() > 1800) {
      m_subsystem.setLowPowerHigh();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setZeroPower();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;

  }

}
