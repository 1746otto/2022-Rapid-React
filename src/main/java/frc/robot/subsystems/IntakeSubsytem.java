package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class IntakeSubsytem {
    CANSparkMax intakeMotor;
    Solenoid pistons;
    boolean intakeState = false; 

    public IntakeSubsytem() {
        intakeMotor = new CANSparkMax(Constants.IntakeConstants.kIntakeMotor, MotorType.kBrushless);
        pistons = new Solenoid(PneumaticsModuleType.REVPH, Constants.IntakeConstants.kIntakeSolenoid );
    }

    public void extend() {
        pistons.set(true);//TODO: test to see if true/false are accurate on 2022 bot
    }

    public void retract() {
        pistons.set(false);//TODO: test to see if true/false accurate on 2022 bot
    }

    public void runFullPower() {
        intakeMotor.set(Constants.IntakeConstants.kIntakeFullPower);
    }

    public void runHalfPower() {
        intakeMotor.set(Constants.IntakeConstants.kIntakerunHalfPower);
    }

    public void runCustomPower() {
        intakeMotor.set(Constants.IntakeConstants.kIntakeCustomPower);
    }

    
}
