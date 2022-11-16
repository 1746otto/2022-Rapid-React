package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
  public double feedForwardVoltage = 0.3;
  public double error = 0;
  public double deltaError = 0;
  public double previousError = 0;
  public double gain = 0.002;
  public double kpGain = 0.5 / ShooterConstants.kSetPointRPMHigh;
  public double kdGain = 0 * kpGain;
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
    return master.getSelectedSensorVelocity(0) * ShooterConstants.kTPSToRPM;
  }

  public void setFullPowerHigh() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
  }

  public void setLowPowerHigh() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kLowGoalSpeed);
  }


  public void highGoalShooter() {
    if (getRPM() < ShooterConstants.kSetPointRPMHigh) {
      setFullPowerHigh();

    } else if (getRPM() > ShooterConstants.kIndexerWindowLow) {
      setLowPowerHigh();

    }
  }

  public void PIDShooterHigh() {
    error = ShooterConstants.kSetPointRPMHigh - getRPM();
    deltaError = error - previousError;
    master.set(ControlMode.PercentOutput, 0.47 + error * kpGain + deltaError * kdGain);
    previousError = error;

  }

  public void setZeroPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
  }
}
