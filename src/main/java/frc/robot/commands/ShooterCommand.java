package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ShooterSubsystem m_subsystem;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ShooterCommand(ShooterSubsystem subsystem) {
      m_subsystem = subsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(subsystem);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // shooterMaster.set(ControlMode.PercentOutput, 1);
        // shooterSlave1.set(ControlMode.PercentOutput, 1);
        // shooterSlave3.set(ControlMode.PercentOutput, 1);
        // shooterSlave2.set(ControlMode.PercentOutput, 1);
      }
    
    
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      m_subsystem.setZeroPower();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
  