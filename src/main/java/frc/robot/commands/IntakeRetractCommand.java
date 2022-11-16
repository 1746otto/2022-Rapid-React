package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeRetractCommand extends CommandBase {
  private final IntakeSubsystem m_subsystem;

  public IntakeRetractCommand(IntakeSubsystem subsystem) {
    m_subsystem = subsystem;
  }

  public void initialize() {
    m_subsystem.retract();
  }

  public void end() {}

  public boolean isFinished() {
    return false;
  }



}


