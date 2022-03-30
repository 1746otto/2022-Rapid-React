package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterHoodConstants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.RobotConstants;


public class ShooterHoodSubsystem extends SubsystemBase {
    private static Solenoid pistons;
    private static boolean retracted = true;

    /**
     * Creates new ShooterHoodSubsystem
     */
    public ShooterHoodSubsystem() {
        pistons = new Solenoid(RobotConstants.kREVPH, PneumaticsModuleType.REVPH,
                ShooterHoodConstants.kShooterHood);
    }

    /**
     * Creates method that checks if hood is retracted or not. If hood is retracted it will return
     * true. If hood is extended it will return false.
     */
    public boolean isRetracted() {
        return retracted;
    }

    /**
     * Retracts (moves back) hood
     */
    public void Retract() {
        pistons.set(ShooterHoodConstants.kShooterHoodRetracted);
        retracted = true;
    }

    /**
     * Extends (moves forward) hood
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
