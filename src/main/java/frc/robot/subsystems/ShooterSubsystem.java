/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class ShooterSubsystem extends SubsystemBase {
  private TalonSRX master;
  private VictorSPX slave1;
  private VictorSPX slave2;
  private VictorSPX slave3;



      /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
      master = new TalonSRX(Ports.SHOOTER_MASTER);
      slave1 = new VictorSPX(Ports.SHOOTER_SLAVE_1);
      slave2 = new VictorSPX(Ports.SHOOTER_SLAVE_2);
      slave3 = new VictorSPX(Ports.SHOOTER_SLAVE_3);

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
      master.set(ControlMode.PercentOutput, 1);
      slave1.set(ControlMode.PercentOutput, 1);
      slave3.set(ControlMode.PercentOutput, 1);
      slave2.set(ControlMode.PercentOutput, 1);
    
     }


     public void setCustomPower(double input) {
      master.set(ControlMode.PercentOutput, input);
      slave1.set(ControlMode.PercentOutput, input);
      slave3.set(ControlMode.PercentOutput, input);
      slave2.set(ControlMode.PercentOutput, input);
    }

     public void setZeroPower() {
      master.set(ControlMode.PercentOutput, 0);
      slave1.set(ControlMode.PercentOutput, 0);
      slave3.set(ControlMode.PercentOutput, 0);
      slave2.set(ControlMode.PercentOutput, 0);
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
/*