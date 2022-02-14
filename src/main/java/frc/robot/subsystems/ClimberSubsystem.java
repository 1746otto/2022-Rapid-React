package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.ClimberConstants;


public class ClimberSubsystem extends SubsystemBase {
    public static double periodic;
    public static final VictorSPX motorR = new VictorSPX(50);
    public static final TalonSRX motorL = new TalonSRX(51);
    public final Solenoid pistons;
    private Object climberPosition;

    public ClimberSubsystem() {    
        //motorR = new VictorSPX(50);
        //motorL = new TalonSRX(51);
        motorL.follow(motorR);
        pistons = new Solenoid(PneumaticsModuleType.REVPH, 2);
        motorR.setSelectedSensorPosition(0.0);
    }

    public static void runClimber() {
      motorR.set(ControlMode.PercentOutput, ClimberConstants.kClimberExtendSpeed);
    }

    public static void stopClimber() {
      motorR.set(ControlMode.PercentOutput, 0);
    }

    

      
    public static boolean isClimberLessThanMax(){
        return motorR.getSelectedSensorPosition() < ClimberConstants.kTopEncoderTicks;
    }

    public void trueClimber() {
        pistons.set(true);
      }
    
      public void falseClimber() {
        pistons.set(false);
      }
    
      public boolean getExtended() {
        return pistons.get();
      }
    
   @Override
   public void periodic(){
     
   } 

    
}
