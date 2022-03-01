package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.IndexerFullForwardCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class IndexerSubsystem extends SubsystemBase {

  private final VictorSPX m_lower;
  private final VictorSPX m_upper;
  // added for tuning can be gotten rid of later
  public double tuningUpperIndexerLimits = 0.25;
  public double tuningLowerIndexerLimits = 0.40;

  /** Creates a new ExampleSubsystem. */

  public IndexerSubsystem() {
    m_lower = new VictorSPX(IndexerConstants.kLower);
    m_upper = new VictorSPX(IndexerConstants.kUpper);
    m_upper.setInverted(true);
  }

  public void runLowerFullForward() {
    // added for tuning
    // m_lower.set(ControlMode.PercentOutput, IndexerConstants.kLowerFullForward);
    m_lower.set(ControlMode.PercentOutput, tuningLowerIndexerLimits);
  }

  public void runUpperFullForward() {
    // added for tuning
    // m_upper.set(ControlMode.PercentOutput, IndexerConstants.kUpperFullForward);
    m_upper.set(ControlMode.PercentOutput, tuningUpperIndexerLimits);
  }

  public void runBothFullForward() {
    runLowerFullForward();
    runUpperFullForward();
  }

  public void runLowerHalfForward() {
    m_lower.set(ControlMode.PercentOutput, IndexerConstants.kLowerHalfForward);
  }

  public void runUpperHalfForward() {
    m_upper.set(ControlMode.PercentOutput, IndexerConstants.kUpperHalfForward);
  }

  public void runBothHalfForward() {
    runLowerHalfForward();
    runUpperHalfForward();
  }

  public void runLowerCustom(double input) {
    m_lower.set(ControlMode.PercentOutput, input);
  }

  public void runUpperCustom(double input) {
    m_upper.set(ControlMode.PercentOutput, input);
  }

  public void runBothCustom(double lowerInput, double upperInput) {
    runLowerCustom(lowerInput);
    runUpperCustom(upperInput);
  }

  public void stopLowerIndexer() {
    m_lower.set(ControlMode.PercentOutput, IndexerConstants.kLowerStop);
  }

  public void stopUpperIndexer() {
    m_upper.set(ControlMode.PercentOutput, IndexerConstants.kUpperStop);
  }

  public void stopBoth() {
    stopLowerIndexer();
    stopUpperIndexer();
  }

  public void runLowerFullBackward() {
    m_lower.set(ControlMode.PercentOutput, -IndexerConstants.kLowerFullForward);
  }

  public void runUpperFullBackward() {
    m_upper.set(ControlMode.PercentOutput, -IndexerConstants.kUpperFullForward);
  }

  public void runBothFullBackward() {
    runLowerFullBackward();
    runUpperFullBackward();
  }

  public void runLowerHalfBackward() {
    m_lower.set(ControlMode.PercentOutput, -IndexerConstants.kLowerHalfForward);
  }

  public void runUpperHalfBackward() {
    m_upper.set(ControlMode.PercentOutput, -IndexerConstants.kUpperHalfForward);
  }

  public void runBothHalfBackward() {
    runLowerHalfBackward();
    runUpperHalfBackward();
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
