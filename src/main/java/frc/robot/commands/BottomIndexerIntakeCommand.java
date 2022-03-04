// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.Constants.IndexerConstants;

/** An example command that uses an example subsystem. */
public class BottomIndexerIntakeCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final IndexerSubsystem m_indexer;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public BottomIndexerIntakeCommand(IndexerSubsystem subsystem) {
        m_indexer = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_indexer.runLowerFullForward();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_indexer.stopLowerIndexer();
        m_indexer.stopOmniWheels();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_indexer.bottomBeamBreakBroken();
    }
}
