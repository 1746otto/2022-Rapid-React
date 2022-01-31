package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class ShooterSubsystem extends SubsystemBase {
  private TalonSRX master;
  private VictorSPX slave1;
  private VictorSPX slave2;
  private VictorSPX slave3;



      /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
      master = new TalonSRX(ShooterConstants.kShooterMaster);
      slave1 = new VictorSPX(ShooterConstants.kShooterSlave1);
      slave2 = new VictorSPX(ShooterConstants.kShooterSlave2);
      slave3 = new VictorSPX(ShooterConstants.kShooterSlave3);

      master.setInverted(true);
      slave1.setInverted(true);
      slave2.setInverted(false);
      slave3.setInverted(true);
        /*

      shooterSlave1.follow(shooterMaster);
      shooterSlave2.follow(shooterMaster);
      shooterSlave3.follow(shooterMaster);
      */
    
      slave1.follow(master);
      slave2.follow(master);
      slave3.follow(master);
  }


    public void setFullPower() {
      master.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
      slave1.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
      slave3.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
      slave2.set(ControlMode.PercentOutput, ShooterConstants.kFullPower);
    
     }


     public void setCustomPower(double input) {
      master.set(ControlMode.PercentOutput, input);
      slave1.set(ControlMode.PercentOutput, input);
      slave3.set(ControlMode.PercentOutput, input);
      slave2.set(ControlMode.PercentOutput, input);
    }

     public void setZeroPower() {
      master.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
      slave1.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
      slave3.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
      slave2.set(ControlMode.PercentOutput, ShooterConstants.kZeroPower);
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
