package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
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
  private final Solenoid extend;
  private final Solenoid disengage;

  public ClimberSubsystem() {
    motorR.setInverted(true);
    motorL.follow(motorR);
    pistons =
        new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH, ClimberConstants.kChannel);
    extend = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
        ClimberConstants.kExtendSolenoidChannel);
    disengage = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
        ClimberConstants.kRetractSolenoidChannel);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("toplimitswitch", isAtTop());
    SmartDashboard.putBoolean("bottomlimitswitch", isAtBottom());
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


  public boolean getEngaged() {
    return pistons.get() == ClimberConstants.kClimberHookEngaged;
  }

  public boolean isAtBottom() {
    return motorL.isFwdLimitSwitchClosed() == 0;
  }

  public boolean isAtTop() {
    return motorL.isRevLimitSwitchClosed() == 0;
  }

  public void extendHighbar() {
    extend.set(true);
  }

  public void lockHighBar() {
    extend.set(false);
  }

  public void disEngageMidPistons() {
    disengage.set(true);
  }

  public void engageMidPistons() {
    disengage.set(false);
  }
}
