package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class Shooter extends SubsystemBase {
  private TalonSRX master;
  private VictorSPX slave1;
  private VictorSPX slave2;
  private VictorSPX slave3;



      /** Creates a new ExampleSubsystem. */
  public Shooter() {
      master = new TalonSRX(Ports.SHOOTER_MASTER);
      slave1 = new VictorSPX(Ports.SHOOTER_SLAVE_1);
      slave2 = new VictorSPX(Ports.SHOOTER_SLAVE_2);
      slave3 = new VictorSPX(Ports.SHOOTER_SLAVE_3);

      /*
      shooterMaster.setInverted(true);
      shooterSlave1.setInverted(true);
      shooterSlave2.setInverted(false);
      shooterSlave3.setInverted(true);
  
      shooterSlave1.follow(shooterMaster);
      shooterSlave2.follow(shooterMaster);
      shooterSlave3.follow(shooterMaster);
      */
    
  
     

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      master.set(ControlMode.PercentOutput, 1);
      slave1.set(ControlMode.PercentOutput, 1);
      slave3.set(ControlMode.PercentOutput, 1);
      slave2.set(ControlMode.PercentOutput, 1);
    }
  
  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
