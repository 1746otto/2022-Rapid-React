package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.RobotConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {
    CANSparkMax intakeMotor;
    Solenoid pistons;
    boolean intakeState = false;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotor, MotorType.kBrushless);
        pistons = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH, IntakeConstants.kIntakeSolenoid);

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
}
