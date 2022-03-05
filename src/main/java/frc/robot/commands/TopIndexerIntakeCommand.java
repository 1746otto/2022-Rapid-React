// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.subsystems.IndexerSubsystem;

/** An example command that uses an example subsystem. */
public class TopIndexerIntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IndexerSubsystem m_indexer;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TopIndexerIntakeCommand(IndexerSubsystem subsystem) {
    m_indexer = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.runBothCustom(IndexerConstants.kLowerCustomPower, IndexerConstants.kUpperCustomPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.stopBoth();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_indexer.topBeamBreakBroken();
  }
}
