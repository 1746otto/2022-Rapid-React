package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class IntakeSubsystem extends SubsystemBase {
    CANSparkMax intakeMotor;
    Solenoid pistons;
    boolean intakeState = false; 

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotor, MotorType.kBrushless);
        pistons = new Solenoid(PneumaticsModuleType.REVPH, IntakeConstants.kIntakeSolenoid );
    }

    public void extend() {
        pistons.set(IntakeConstants.kIntakeExtended);//TODO: test to see if true/false are accurate on 2022 bot
    }

    public void retract() {
        pistons.set(IntakeConstants.kIntakeRetracted);//TODO: test to see if true/false accurate on 2022 bot
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
