package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.ClimberConstants;


public class ClimberExtendCommand extends CommandBase {
    private ClimberSubsystem m_climber = new ClimberSubsystem();
    
    public ClimberExtendCommand(ClimberSubsystem subsystem) {
        m_climber = subsystem;

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
    m_climber.runClimber();
    

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
  

  
  

  
  
