package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterHighLowCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public final ShooterHoodSubsystem m_hood;
    public final ShooterSubsystem m_shooter;

    /**
     * Creates a new ShooterHighLowCommand. Sets Shooter RPM for low goal shot when hood is extended
     * (forward) Sets Shooter RPM for high goal shot when hood is retracted (back)
     * 
     * @param hoodSubsystem The subsystem used by this command.
     */
    public ShooterHighLowCommand(ShooterHoodSubsystem hoodSubsystem,
            ShooterSubsystem shooterSubsystem) {
        m_hood = hoodSubsystem;
        m_shooter = shooterSubsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_shooter, m_hood);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (m_hood.isRetracted() == true) {
            m_shooter.highGoalShooter();
        } else if (m_hood.isRetracted() == false) {
            m_shooter.lowGoalShooter();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void execute() {
        if (m_hood.isRetracted() == true) {
            m_shooter.highGoalShooter();
        } else if (m_hood.isRetracted() == false) {
            m_shooter.lowGoalShooter();
        }
    }

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
