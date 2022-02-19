package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private final VictorSPX motorR = new VictorSPX(50);
    private final TalonSRX motorL = new TalonSRX(51);
    private final Solenoid pistons;
    private final DigitalInput topLimitSwitch = new DigitalInput(ClimberConstants.kTopLimitSwitch);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(ClimberConstants.kBottomLimitSwitch);

    public ClimberSubsystem() {    
      motorL.follow(motorR);
      pistons = new Solenoid(PneumaticsModuleType.REVPH, ClimberConstants.kChannel);
      motorR.setSelectedSensorPosition(ClimberConstants.kResetSensorPosition);
    }

    public void runExtendClimber() {
      motorR.set(ControlMode.PercentOutput, ClimberConstants.kClimberExtendSpeed);
    }

    public void runRetractClimber() {
      motorR.set(ControlMode.PercentOutput, ClimberConstants.kClimberRetractSpeed);
    }

    public void stopClimber() {
      motorR.set(ControlMode.PercentOutput, 0);
    }

    public void engageClimberHook() {
      pistons.set(ClimberConstants.climberHookEngage);
    }

    public void releaseClimberHook() {
      pistons.set(ClimberConstants.climberHookReleased);
    }
    
    public boolean getExtended() {
      return pistons.get();
    }

    public boolean topLimitSwitchClick() {
      return topLimitSwitch.get();
    }

    public boolean bottomLimitSwitchClick() {
      return bottomLimitSwitch.get();
    }
}
