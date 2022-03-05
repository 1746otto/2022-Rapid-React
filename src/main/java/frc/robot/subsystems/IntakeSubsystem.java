package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.RobotConstants;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax intakeMotor;
  CANSparkMax intakeOmniWheels;
  Solenoid pistons;
  boolean intakeState = false;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotor, MotorType.kBrushless);
    intakeOmniWheels = new CANSparkMax(IntakeConstants.kIntakeOmniWheels, MotorType.kBrushless);
    pistons = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
        IntakeConstants.kIntakeSolenoid);
    intakeOmniWheels.follow(intakeMotor);

  }

  public void extend() {
    pistons.set(IntakeConstants.kIntakeExtended);
  }

  public void retract() {
    pistons.set(IntakeConstants.kIntakeRetracted);
  }

  public void runFullPower() {
    intakeMotor.set(IntakeConstants.kIntakeFullPower);
  }

  public void runCustomPower(double input) {
    intakeMotor.set(input);
  }

  public void runZeroPower() {
    intakeMotor.set(IntakeConstants.kIntakerunZeroPower);
  }

  public void turnOnIntake() {
    extend();
    runCustomPower(0.5);
  }

  public void turnOffIntake() {
    runZeroPower();
    retract();
  }
}
