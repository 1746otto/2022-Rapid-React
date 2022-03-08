package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterCustomRPMCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public final ShooterSubsystem m_subsystem;
  public double m_RPM;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCustomRPMCommand(ShooterSubsystem subsystem, double RPM) {
    m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setRPM(m_RPM);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("kP", m_subsystem.kP);
    SmartDashboard.putNumber("kI", m_subsystem.kI);
    SmartDashboard.putNumber("kD", m_subsystem.kD);
    SmartDashboard.putNumber("kF", m_subsystem.kF);
    SmartDashboard.putNumber("RPM", m_subsystem.getRPM());
    SmartDashboard.putNumber("Error", m_subsystem.getRPM() - m_RPM);
    SmartDashboard.putNumber("Percent Error", (m_subsystem.getRPM() - m_RPM) / m_RPM);
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
