package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.IndexerFullForwardCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class IndexerSubsystem extends SubsystemBase {
 
  private TalonSRX frontSide;



      /** Creates a new ExampleSubsystem. */
  public IndexerSubsystem() {
    frontSide = new TalonSRX(Constants.ShooterConstants.kIndexerMotor);

  }
  public void runFullForward() {
    frontSide.set(ControlMode.PercentOutput, Constants.IndexerConstants.kHalfForward);
  }

  public void runHalfForward() {
    frontSide.set(ControlMode.PercentOutput, Constants.IndexerConstants.kHalfForward );
  }

  public void runCustom(double input) {
    frontSide.set(ControlMode.PercentOutput, -input);
  }
  public void stopIndexer() {
    frontSide.set(ControlMode.PercentOutput, Constants.IndexerConstants.kStop);

  }



  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
