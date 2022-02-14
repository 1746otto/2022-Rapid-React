package frc.robot.subsystems;

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
        pistons = new Solenoid(null, Constants.IntakeConstants.kIntakeSolenoid );
        
    }
}
