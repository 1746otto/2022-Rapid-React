package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class HighClimberReleaseCommand extends CommandBase {
  private final ClimberSubsystem m_climber;

  public HighClimberReleaseCommand(ClimberSubsystem subsystem) {
    m_climber = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_climber.stopExtendHigh();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}


