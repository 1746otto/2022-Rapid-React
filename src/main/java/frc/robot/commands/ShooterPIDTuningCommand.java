package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterPIDTuningCommand extends CommandBase {
  public ShooterCustomRPMCommand m_shooterCustomRPMCommand;

  public ShooterPIDTuningCommand(ShooterCustomRPMCommand command) {
    m_shooterCustomRPMCommand = command;
  }

  @Override
  public void execute() {
    m_shooterCustomRPMCommand.m_subsystem.kP =
        SmartDashboard.getNumber("kP", m_shooterCustomRPMCommand.m_subsystem.kP);
    m_shooterCustomRPMCommand.m_subsystem.kD =
        SmartDashboard.getNumber("kD", m_shooterCustomRPMCommand.m_subsystem.kD);
    m_shooterCustomRPMCommand.m_subsystem.kI =
        SmartDashboard.getNumber("kI", m_shooterCustomRPMCommand.m_subsystem.kI);
    m_shooterCustomRPMCommand.m_subsystem.kF =
        SmartDashboard.getNumber("kF", m_shooterCustomRPMCommand.m_subsystem.kF);
  }
}
