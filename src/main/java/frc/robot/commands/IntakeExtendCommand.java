package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeExtendCommand extends CommandBase {
  private final IntakeSubsystem m_subsystem;

  public IntakeExtendCommand(IntakeSubsystem subsystem) {
    m_subsystem = subsystem;
  }

  public void initialize() {
    m_subsystem.extend();
  }

  public void end() {
    m_subsystem.retract();
  }

  public boolean isFinished() {
    return false;
  }



}

