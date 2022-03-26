package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterHoodConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.RobotConstants;


public class ShooterHoodSubsystem extends SubsystemBase {
    private static Solenoid pistons;
    private static boolean retracted = true;


    public ShooterHoodSubsystem() {
        pistons = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
                ShooterHoodConstants.kShooterHood);

        /*
         * name variables here master = new TalonSRX(ShooterConstants.kShooterMaster);
         */
    }


    public boolean isRetracted() {
        return retracted;
    }

    public void Retract() {
        pistons.set(ShooterHoodConstants.kShooterHoodRetracted);
        retracted = true;
    }

    /**
     * Sets shooter power for lowgoal shot
     */
    public void Extend() {
        pistons.set(ShooterHoodConstants.kShooterHoodExtended);
        retracted = false;
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }


}
