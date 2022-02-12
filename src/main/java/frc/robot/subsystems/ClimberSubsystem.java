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
    private final VictorSPX motorR;
    private final TalonSRX motorL;
    private final Solenoid pistons;

    public ClimberSubsystem() {    
        motorR = new VictorSPX(50);
        motorL = new TalonSRX(51);
        motorL.follow(motorR);
        pistons = new Solenoid(PneumaticsModuleType.REVPH, 2);
        motorR.setSelectedSensorPosition(0.0);
    }

    public void ClimberExtend(double climberVertical) {
        if (motorR.getSelectedSensorPosition() < ClimberConstants.kTopEncoderTicks && climberVertical > 0) {
            motorR.set(ControlMode.PercentOutput, 0);
        }
        else {
            motorR.set(ControlMode.PercentOutput, climberVertical);
        }
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
    
    

    
}
