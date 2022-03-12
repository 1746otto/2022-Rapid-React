package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

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
    return master.getSelectedSensorVelocity() * 600 / 4096;
  }

  public void setFullPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
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
    System.out.println("RPM: " + getRPM());

    // This method will be called once per scheduler run
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
