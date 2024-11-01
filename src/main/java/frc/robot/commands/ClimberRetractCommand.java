package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberRetractCommand extends CommandBase {
  private final ClimberSubsystem m_climber;

  public ClimberRetractCommand(ClimberSubsystem subsystem) {
    m_climber = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_climber.runRetractClimber();
  }

  @Override
  public void end(boolean interrupted) {
    m_climber.stopClimber();
  }

  @Override
  public boolean isFinished() {
    return !m_climber.isAtBottom();
  }
}


