package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterHoodSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterHoodToggleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterHoodSubsystem m_subsystem;

  /**
   * Creates new ShooterHoodToggleCommand
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterHoodToggleCommand(ShooterHoodSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  /**
   * Called when the command is initially scheduled. If the hood is extended, it retracts the hood.
   * If the hood is retracted it extends the hood.
   */
  @Override
  public void initialize() {
    if (m_subsystem.isRetracted() == true) {
      m_subsystem.Extend();
    } else if (m_subsystem.isRetracted() == false) {
      m_subsystem.Retract();
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
