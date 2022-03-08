package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterPIDTuningCommand extends CommandBase {
  public ShooterFullPowerCommand m_shooterFullPowerCommand;

  public ShooterPIDTuningCommand(ShooterFullPowerCommand command) {
    m_shooterFullPowerCommand = command;
  }

  @Override
  public void execute() {
    m_shooterFullPowerCommand.m_subsystem.kP =
        SmartDashboard.getNumber("kP", m_shooterFullPowerCommand.m_subsystem.kP);
    m_shooterFullPowerCommand.m_subsystem.kD =
        SmartDashboard.getNumber("kD", m_shooterFullPowerCommand.m_subsystem.kD);
    m_shooterFullPowerCommand.m_subsystem.kI =
        SmartDashboard.getNumber("kI", m_shooterFullPowerCommand.m_subsystem.kI);
    m_shooterFullPowerCommand.m_subsystem.kF =
        SmartDashboard.getNumber("kF", m_shooterFullPowerCommand.m_subsystem.kF);
  }
}
