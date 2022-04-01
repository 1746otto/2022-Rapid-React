package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.ShooterConstants;

public class IndexerSubsystem extends SubsystemBase {

  private final VictorSPX m_lower;
  private final VictorSPX m_upper;
  private final AnalogInput beambreakTop;
  private final AnalogInput beambreakBottom;
  private final ShooterSubsystem m_shooter;

  private boolean beambreakTopLastState = false;
  private boolean beambreakBottomLastState = false;

  /** Creates a new ExampleSubsystem. */
  public IndexerSubsystem(ShooterSubsystem shooter) {
    m_lower = new VictorSPX(IndexerConstants.kLower);
    m_upper = new VictorSPX(IndexerConstants.kUpper);
    m_lower.setInverted(true);
    beambreakTop = new AnalogInput(IndexerConstants.kBeamBreakTop);
    beambreakBottom = new AnalogInput(IndexerConstants.kBeamBreakBottom);
    m_shooter = shooter;
  }

  public void runLowerFullForward() {
    m_lower.set(ControlMode.PercentOutput, IndexerConstants.kLowerFullForward);
  }

  public void runUpperFullForward() {
    m_upper.set(ControlMode.PercentOutput, IndexerConstants.kUpperFullForward);

  }

  public void runBothFullForward() {
    runLowerFullForward();
    runUpperFullForward();

  }

  public void runHighGoalIndexer() {
    if (m_shooter.getRPM() > ShooterConstants.kHGLowRPM
        && m_shooter.getRPM() < ShooterConstants.kHGHighRPM) {
      if (topBeamBreakBroken()) {
        runUpperFullForward();
      } else {
        runBothFullForward();
      }
    } else {
      // stopUpperIndexer();
    }
  }

  public void runLowGoalIndexer() {
    if (m_shooter.getRPM() > ShooterConstants.kLGLowRPM
        && m_shooter.getRPM() < ShooterConstants.kLGHighRPM) {
      if (topBeamBreakBroken()) {
        runUpperFullForward();
      } else {
        runBothFullForward();
      }
    } else {
      // stopUpperIndexer();
    }
  }

  /*
   * if (m_shooter.getRPM() > ShooterConstants.kLGLowRPM && m_shooter.getRPM() <
   * ShooterConstants.kLGHighRPM) { if (topBeamBreakBroken()) { runUpperFullForward(); } else {
   * runBothFullForward(); } }
   */


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

  public boolean topBeamBreakBroken() {
    return beambreakTopLastState;
  }

  public boolean bottomBeamBreakBroken() {
    return beambreakBottomLastState;
  }

  @Override
  public void periodic() {
    beambreakBottomLastState = (Math.floor(beambreakBottom.getVoltage()) == 0);
    beambreakTopLastState = (Math.floor(beambreakTop.getVoltage()) == 0);

  }
}
