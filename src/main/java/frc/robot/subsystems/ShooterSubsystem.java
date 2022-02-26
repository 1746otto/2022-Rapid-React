package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class ShooterSubsystem extends SubsystemBase {
  private VictorSPX master;
  private TalonSRX slave1;



      /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
      master = new VictorSPX(ShooterConstants.kShooterMaster);
      slave1 = new TalonSRX(ShooterConstants.kShooterSlave1);
      
      slave1.setInverted(false);
      slave1.follow(master);
  }


    public void setFullPower() {
      master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
     }


     public void setCustomPower(double input) {
      master.set(ControlMode.PercentOutput, input);
    }

     public void setZeroPower() {
      master.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
    }
}
