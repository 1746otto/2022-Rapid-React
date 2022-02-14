package frc.robot.commands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberRetractCommand extends CommandBase {

    private ClimberSubsystem m_climber = new ClimberSubsystem();
    private final DigitalInput topLimitSwitch = new DigitalInput(0);

    public ClimberRetractCommand(ClimberSubsystem subsystem) {
        m_climber = subsystem;

        addRequirements(subsystem);
    }

  @Override
  public void initialize() {
    m_climber.runRetractClimber();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    m_climber.stopClimber();
  }

  @Override 
  public boolean isFinished(){
    return (!m_climber.isClimberNonZero());
  }
}
  

  
  

  
  
