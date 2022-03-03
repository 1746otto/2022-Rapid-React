package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;

public class IndexerSubsystem extends SubsystemBase {

  private final VictorSPX m_wheel;
  private final VictorSPX m_belt;
  private final AnalogInput beambreakTop;
  private final AnalogInput beambreakBottom;

  private boolean beambreakTopLastState = false;
  private boolean beambreakBottomLastState = false;
  private boolean hopperDisabled = false;

  /** Creates a new ExampleSubsystem. */

  public IndexerSubsystem() {
    m_wheel = new VictorSPX(IndexerConstants.kWheel);
    m_belt = new VictorSPX(IndexerConstants.kBelt);
    m_belt.setInverted(true);
    beambreakTop = new AnalogInput(IndexerConstants.kBeambreakTop);
    beambreakBottom = new AnalogInput(IndexerConstants.kBeambreakBottom);
  }

  public void runWheelFullForward() {
    m_wheel.set(ControlMode.PercentOutput, IndexerConstants.kWheelFullForward);

  }

  public void runBeltFullForward() {
    m_belt.set(ControlMode.PercentOutput, IndexerConstants.kBeltFullForward);
  }

  public void runBothFullForward() {
    runWheelFullForward();
    runBeltFullForward();
  }

  public void runWheelHalfForward() {
    m_wheel.set(ControlMode.PercentOutput, IndexerConstants.kWheelHalfForward);
  }

  public void runBeltHalfForward() {
    m_belt.set(ControlMode.PercentOutput, IndexerConstants.kBeltHalfForward);
  }

  public void runBothHalfForward() {
    runWheelHalfForward();
    runBeltHalfForward();
  }

  public void runWheelCustom(double input) {
    m_wheel.set(ControlMode.PercentOutput, input);
  }

  public void runBeltCustom(double input) {
    m_belt.set(ControlMode.PercentOutput, input);
  }

  public void runBothCustom(double wheelInput, double beltInput) {
    runWheelCustom(wheelInput);
    runBeltCustom(beltInput);
  }

  public void stopWheelIndexer() {
    m_wheel.set(ControlMode.PercentOutput, IndexerConstants.kWheelStop);
  }

  public void stopBeltIndexer() {
    m_belt.set(ControlMode.PercentOutput, IndexerConstants.kBeltStop);
  }

  public void stopBoth() {
    stopWheelIndexer();
    stopBeltIndexer();
  }

  public void runWheelFullBackward() {
    m_wheel.set(ControlMode.PercentOutput, -IndexerConstants.kWheelFullForward);
  }

  public void runBeltFullBackward() {
    m_belt.set(ControlMode.PercentOutput, -IndexerConstants.kBeltFullForward);
  }

  public void runBothFullBackward() {
    runWheelFullBackward();
    runBeltFullBackward();
  }

  public void runWheelHalfBackward() {
    m_wheel.set(ControlMode.PercentOutput, -IndexerConstants.kWheelHalfForward);
  }

  public void runBeltHalfBackward() {
    m_belt.set(ControlMode.PercentOutput, -IndexerConstants.kBeltHalfForward);
  }

  public void runBothHalfBackward() {
    runWheelHalfBackward();
    runBeltHalfBackward();
  }

  public boolean topBeamBreak() {
    return beambreakTopLastState;
  }

  public boolean bottomBeamBreak() {
    return beambreakBottomLastState;
  }

  @Override
  public void periodic() {
    /*
     * if (Math.floor(beambreakTop.getVoltage()) == 0) {
     * if (!beambreakTopLastState) {
     * hopperDisabled = true;
     * beambreakTopLastState = true;
     * }
     * else {
     * beambreakTopLastState = false;
     * }
     * }
     * if (Math.floor(beambreakBottom.getVoltage()) == 0) {
     * if (!beambreakBottomLastState) {
     * hopperDisabled = true;
     * beambreakBottomLastState = true;
     * }
     * else {
     * beambreakBottomLastState = false;
     * }
     * }
     * }
     */
    beambreakBottomLastState = (Math.floor(beambreakBottom.getVoltage()) == 0);
    beambreakTopLastState = (Math.floor(beambreakTop.getVoltage()) == 0);
    System.out.println("Bottom state:" + beambreakBottomLastState);
    System.out.println("Top state" + beambreakTopLastState);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
