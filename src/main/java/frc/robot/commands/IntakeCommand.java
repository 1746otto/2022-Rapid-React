// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_intake;
  private final IndexerSubsystem m_indexer;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeCommand(IntakeSubsystem subsystemIntake, IndexerSubsystem subsystemIndexer) {
    m_intake = subsystemIntake;
    m_indexer = subsystemIndexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystemIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.extend();
    m_intake.runCustomPower(IntakeConstants.kIntakeCustomPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.runZeroPower();
    m_intake.retract();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_indexer.bottomBeamBreakBroken();
  }
}
