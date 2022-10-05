package frc.robot.commands;

import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberExtendCommand extends CommandBase {
  private final ClimberSubsystem m_climber;

  public ClimberExtendCommand(ClimberSubsystem subsystem) {
    m_climber = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_climber.runExtendClimber();

  }

  @Override
  public void execute() {
    SmartDashboard.putBoolean("toplimitswitch", m_climber.isAtTop());
    SmartDashboard.putBoolean("limitswitch", m_climber.isAtTop());
  }

  @Override
  public void end(boolean interrupted) {
    m_climber.stopClimber();
  }

  @Override
  public boolean isFinished() {
    return !m_climber.isAtTop();
  }
}


