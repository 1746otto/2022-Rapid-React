package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class IndexerFullForwardCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IndexerSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public IndexerFullForwardCommand(IndexerSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  Timer m_timer;

  @Override
  public void initialize() {
    m_timer.start();
    if (((m_subsystem.topBeamBreakBroken()) && !(m_subsystem.bottomBeamBreakBroken()))
        && (m_timer.getFPGATimestamp() == 0.1 && m_timer.getFPGATimestamp() <= 1)) {
      m_subsystem.runHighGoalIndexer();
      m_timer.reset();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopBoth();
  }

  @Override
  public void execute() {
    m_timer.start();
    if (((m_subsystem.topBeamBreakBroken()) && !(m_subsystem.bottomBeamBreakBroken()))
        && (m_timer.getFPGATimestamp() >= 0.1 && m_timer.getFPGATimestamp() <= 1)) {
      m_subsystem.runHighGoalIndexer();
      m_timer.reset();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
