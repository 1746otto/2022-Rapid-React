package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterHoodSubsystem;

public class ShooterHoodRetractCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ShooterHoodSubsystem m_subsystem;

    /**
     * Creates a new ShooterHoodRetractCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ShooterHoodRetractCommand(ShooterHoodSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    /**
     * Called when the command is initially scheduled. Retracts hood.
     */

    @Override
    public void initialize() {
        m_subsystem.Retract();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
