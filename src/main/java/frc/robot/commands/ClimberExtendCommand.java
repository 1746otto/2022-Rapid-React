package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.ClimberConstants;
import edu.wpi.first.wpilibj.XboxController;

public class ClimberExtendCommand extends CommandBase {
    private ClimberSubsystem m_climber = new ClimberSubsystem();
    private final XboxController m_controller;
    
    public ClimberExtendCommand(ClimberSubsystem subsystem, XboxController controller) {
        m_climber = subsystem;
        m_controller = controller;
        addRequirements(subsystem);
    }

    /*public void ClimberExtend(double climberVertical) {
        if (motorR.getSelectedSensorPosition() < ClimberConstants.kTopEncoderTicks && climberVertical > 0) {
            runClimber();
        }
        else {
            motorR.set(ControlMode.PercentOutput, climberVertical);
        }
    }*/

  @Override
  public void initialize() {
    if(m_controller.getAButton()){
        m_climber.runClimber();
    }

}

  @Override
  public void execute() {
      
    }
  @Override 
  public boolean isFinished(){
      if(!m_climber.climberPosition()){
          m_climber.stopClimber();
          return false;
      }
    return true;

  }
}
  

  
  

  
  
