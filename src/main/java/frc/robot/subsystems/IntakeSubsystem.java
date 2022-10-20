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
  Solenoid pistons;
  boolean intakeState = false;
  CANSparkMax omniWheels;
  // IndexerSubsystem m_indexer;

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

  public void runCustomPower(double input) {
    intakeMotor.set(input); // Note change in intake
    omniWheels.set(input);
  }

  public void runZeroPower() {
    intakeMotor.stopMotor();
    omniWheels.stopMotor();
  }

  public void turnOnIntake() {
    extend();
    runCustomPower(IntakeConstants.kIntakeCustomPower);
  }

  public void turnOffIntake() {
    runZeroPower();
    retract();
  }

  @Override
  public void periodic() {}
}
