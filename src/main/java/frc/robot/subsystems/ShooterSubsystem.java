package frc.robot.subsystems;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import java.util.function.BooleanSupplier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class ShooterSubsystem extends SubsystemBase {
  public TalonSRX master;
  private VictorSPX slave1;
  public double kP = 0.639375 / 7;
  public double kF = 0.0;
  public double kD = 55 * kP;
  public double kI = 0.0;
  public double m_RPM;
  public double feedForwardVoltage = 0.5;
  public double error = 0;
  public double gain = 0.2;
  public boolean RPMShotTune;
  public static boolean RPMShotValid = false;
  public static Timer timer;
  public double slope = 0.3;
  public double intercept = 0.1;

  // Calculate manually or with https://www.reca.lc/flywheel
  public double kS = 1;
  public double kV = 1;
  public double kA = 1;

  public double velocity = 10; // units/second
  public double accel = 10; // units/second/second

  SimpleMotorFeedforward m_feedforward = new SimpleMotorFeedforward(kS, kV, kA);

  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
    master = new TalonSRX(ShooterConstants.kShooterMaster);
    slave1 = new VictorSPX(ShooterConstants.kShooterSlave1);

    slave1.setInverted(false);
    slave1.follow(master);
    master.config_kF(0, kF);
    master.config_kP(0, kP);
    master.config_kI(0, kI);
    master.config_kD(0, kD);
  }

  public double getRPM() {

    return master.getSelectedSensorVelocity(1) * ShooterConstants.kTPSToRPM;

  }

  public void setFullPowerHigh() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
  }

  public void setLowPowerHigh() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kLowGoalSpeed);
  }

  public void setFullPowerLow() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kFullPowerLow);
  }

  public void setLowPowerLow() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kLowPowerLow);
  }

  public void highGoalShooter() {
    if (getRPM() < ShooterConstants.kHGHighRPM) {
      setFullPowerHigh();

    } else if (getRPM() > ShooterConstants.kHGLowRPM) {
      setLowPowerHigh();

    }
  }

  public void runFeedForward() {
    master.set(ControlMode.PercentOutput, m_feedforward.calculate(velocity, accel));
  }

  public void exponentialShooter() {
    master.set(ControlMode.PercentOutput, feedForwardVoltage + gain * Math.exp(error - 1));
  }

  public void linearShooter() {
    master.set(ControlMode.PercentOutput, slope * error + intercept);
  }

  public void quadraticShooter() {
    master.set(ControlMode.PercentOutput, (error *= Math.abs(error)) * slope + intercept);
  }

  // Sets shooter power for high goal shot.

  public void lowGoalShooter() {
    if (getRPM() < ShooterConstants.kLGHighRPM) {
      setFullPowerLow();

    } else if (getRPM() > ShooterConstants.kLGLowRPM) {
      setLowPowerLow();

    }
  }

  /*
   * if (getRPM() < ShooterConstants.kLGHighRPM) { setFullPowerLow(); } else if (getRPM() >
   * ShooterConstants.kLGLowRPM) { setLowPowerLow(); }
   */


  /**
   * Sets shooter power for lowgoal shot
   */

  public boolean getRPMShot() {
    return RPMShotTune;
  }

  public boolean getRPMValid() {
    return RPMShotValid;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("kP", kP);
    SmartDashboard.putNumber("kI", kI);
    SmartDashboard.putNumber("kD", kD);
    SmartDashboard.putNumber("kF", kF);
    SmartDashboard.putNumber("RPM", getRPM());
    SmartDashboard.putNumber("Error", getRPM() - m_RPM);
    SmartDashboard.putNumber("Percent Error", (getRPM() - m_RPM) / m_RPM);

    // System.out.println("RPM: " + getRPM());
    RPMShotTune = getRPM() < 1800;
    RPMShotValid = getRPM() > 1600;
    System.out.println(getRPM());

    // System.out.println("RPM shot valid: " + RPMShotValid);

    // This method will be called once per scheduler run
    error = ((ShooterConstants.kHGHighRPM) - getRPM()) / (ShooterConstants.kHGHighRPM);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setCustomPower(double input) {
    master.set(ControlMode.PercentOutput, input);
  }

  public void setZeroPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
  }

  public void setRPM(double rpm) {
    m_RPM = rpm;
    double velRequest = rpm * ShooterConstants.kRPMToTPS / .6;
    master.set(ControlMode.Velocity, velRequest);
  }


}
