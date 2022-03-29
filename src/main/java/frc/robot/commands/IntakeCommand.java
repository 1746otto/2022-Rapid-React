// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_intake;


  /**
   * This command is used to extend and run the intake, and stops and retracts the intake once the
   * command ends.
   *
   * @param subsystemIntake The subsystem used by this command.
   */
  public IntakeCommand(IntakeSubsystem subsystemIntake) {
    m_intake = subsystemIntake;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystemIntake);
  }

  // Extends and runs intake at a custom power defined by a constant.
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
}
