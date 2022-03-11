package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.RobotConstants;

public class ClimberSubsystem extends SubsystemBase {
  private final VictorSPX motorR = new VictorSPX(ClimberConstants.kMotorR);
  private final TalonSRX motorL = new TalonSRX(ClimberConstants.kMotorL);
  private final Solenoid pistons;
  public final DigitalInput topLimitSwitch = new DigitalInput(ClimberConstants.kTopLimitSwitch);
  public final DigitalInput bottomLimitSwitch =
      new DigitalInput(ClimberConstants.kBottomLimitSwitch);

  public ClimberSubsystem() {
    motorR.setInverted(true);
    motorL.follow(motorR);
    pistons =
        new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH, ClimberConstants.kChannel);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("toplimitswitch", motorL.isFwdLimitSwitchClosed() == 1);
    SmartDashboard.putBoolean("bottomlimitswitch", motorL.isRevLimitSwitchClosed() == 1);
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
    pistons.set(ClimberConstants.kClimberHookEngaged);
  }

  public void releaseClimberHook() {
    pistons.set(ClimberConstants.kClimberHookReleased);
  }

  public boolean getEngaged() {
    return pistons.get() == ClimberConstants.kClimberHookEngaged;
  }

  public boolean isAtTop() {
    return 
    //return motorL.isFwdLimitSwitchClosed() == 1;
  }

  public boolean isAtBottom() {
    // return motorL.isRevLimitSwitchClosed() == 1;
  }
}
