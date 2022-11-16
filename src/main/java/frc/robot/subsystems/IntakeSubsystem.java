package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.RobotConstants;



public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax intakeMotor;
  Solenoid pistons;
  boolean intakeState = false;
  CANSparkMax omniWheels;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotor, MotorType.kBrushless);
    pistons = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
        IntakeConstants.kIntakeSolenoid);
    omniWheels = new CANSparkMax(IntakeConstants.kIntakeOmniWheels, MotorType.kBrushless);
    omniWheels.setInverted(true);

  }

  public void extend() {
    pistons.set(IntakeConstants.kIntakeExtended);
  }

  public void retract() {
    pistons.set(IntakeConstants.kIntakeRetracted);
  }

  public void runFullPower() {
    intakeMotor.set(IntakeConstants.kIntakeFullPower);
    omniWheels.set(IntakeConstants.kIntakeFullPower);
  }

  public void runZeroPower() {
    intakeMotor.stopMotor();
    omniWheels.stopMotor();
  }

  public void turnOffIntake() {
    runZeroPower();
    retract();
  }

  public void turnOnIntake() {
    extend();
    runFullPower();
  }



}
