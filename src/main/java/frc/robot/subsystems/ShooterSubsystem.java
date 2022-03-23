package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class ShooterSubsystem extends SubsystemBase {
  private TalonSRX master;
  private VictorSPX slave1;
  private boolean RPMShot = true;



  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
    master = new TalonSRX(ShooterConstants.kShooterSlave1);
    slave1 = new VictorSPX(ShooterConstants.kShooterMaster);

    slave1.setInverted(false);
    slave1.follow(master);
  }

  /**
   * Sets the power level of the shooter motor {@TalonSRX} at full power as defined in our
   * constants.
   */
  public void setFullPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
  }

  public void setHalfPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kHalfPower);
  }

  public void setCustomPower(double input) {
    master.set(ControlMode.PercentOutput, input);
  }

  public void setZeroPower() {
    master.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
  }

  public boolean getRPMShotValue() {
    return RPMShot;
  }

  @Override
  public void periodic() {
    if (master.getSelectedSensorVelocity() < 10000) {
      RPMShot = false;
    } else if (master.getSelectedSensorVelocity() > 15000) {
      RPMShot = true;
    }
    System.out.println(RPMShot);
    System.out.println(master.getSelectedSensorVelocity());
  }
}
