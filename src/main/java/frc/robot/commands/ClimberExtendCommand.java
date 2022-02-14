package frc.robot.commands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.ClimberConstants;

public class ClimberExtendCommand extends CommandBase {

    private ClimberSubsystem m_climber = new ClimberSubsystem();
    private final DigitalInput topLimitSwitch = new DigitalInput(ClimberConstants.kTopLimitSwitch);

    public ClimberExtendCommand(ClimberSubsystem subsystem) {
        m_climber = subsystem;

        addRequirements(subsystem);
    }

  @Override
  public void initialize() {
    m_climber.runExtendClimber();
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
    return (!m_climber.isClimberLessThanMax() || topLimitSwitch.get());
  }
}
  

  
  

  
  
