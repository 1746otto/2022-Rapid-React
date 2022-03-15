package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeCommand extends CommandBase {
  IndexerSubsystem m_indexerSubsystem;
  IntakeSubsystem m_intakeSubsystem;

  public OuttakeCommand(IndexerSubsystem indexer, IntakeSubsystem intake) {
    m_indexerSubsystem = indexer;
    m_intakeSubsystem = intake;
  }

  @Override
  public void initialize() {
    m_indexerSubsystem.runLowerFullBackward();
    m_intakeSubsystem.extend();
    m_intakeSubsystem.runCustomPower(-IntakeConstants.kIntakeFullPower);
  }

  @Override
  public void end(boolean interrupted) {
    m_indexerSubsystem.runBothCustom(0, 0);
    m_intakeSubsystem.retract();
    m_intakeSubsystem.runZeroPower();
  }

}
