package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterHighLowCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public final ShooterSubsystem m_shooter;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ShooterHighLowCommand(ShooterSubsystem subsystem) {
        m_shooter = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (m_shooter.shooterHoodOpen) {
            m_shooter.setFullPower();
        } else {
            m_shooter.setLowPower();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.setZeroPower();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;

    }

}
