package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;
// remove later
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/** An example command that uses an example subsystem. */
public class IndexerLowerBeltForwardCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final IndexerSubsystem m_subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public IndexerLowerBeltForwardCommand(IndexerSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // commented out for tuning
        // m_subsystem.runBothFullForward();
        SmartDashboard.putNumber("IndexerLowerLimits: ", m_subsystem.tuningLowerIndexerLimits);
    }

    // Called every time the scheduler runs while the command is scheduled.
    // added in for tuning, remove when done
    @Override
    public void execute() {

        m_subsystem.runLowerFullForward();;
        m_subsystem.tuningLowerIndexerLimits = SmartDashboard.getNumber("IndexerLowerLimits: ",
                m_subsystem.tuningLowerIndexerLimits);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopBoth();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
