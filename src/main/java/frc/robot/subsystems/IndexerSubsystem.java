package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class IndexerSubsystem extends SubsystemBase {
 
  private TalonSRX frontSide;



      /** Creates a new ExampleSubsystem. */
  public IndexerSubsystem() {
    frontSide = new TalonSRX(Ports.INDEXER_MOTOR);

  }
  public void runFullForward() {
    frontSide.set(ControlMode.PercentOutput, -1.0);
  }

  public void runHalfForward() {
    frontSide.set(ControlMode.PercentOutput, -0.5);
  }

  public void runCustom(double input) {
    frontSide.set(ControlMode.PercentOutput, -input);
  }
  public void stopIndexer() {
    frontSide.set(ControlMode.PercentOutput, 0.0);

  }



  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
     runFullForward();
  }
  
  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
