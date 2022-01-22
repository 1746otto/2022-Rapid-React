package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveSubsystem extends SubsystemBase {
  CANSparkMax right1;
  CANSparkMax right2;
  CANSparkMax left1;
  CANSparkMax left2;
  
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
    right1 = new CANSparkMax(DriveConstants.RIGHT_MOTOR1, MotorType.kBrushless);
    right2 = new CANSparkMax(DriveConstants.RIGHT_MOTOR2, MotorType.kBrushless);
    left1 = new CANSparkMax(DriveConstants.LEFT_MOTOR1, MotorType.kBrushless);
    left2 = new CANSparkMax(DriveConstants.LEFT_MOTOR2, MotorType.kBrushless);
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void driveRun(double DriveAxis, double SteerAxis) {
    double magnitude = Math.sqrt(2);
    right1.set(((DriveAxis - SteerAxis) / magnitude));
    right2.set(((DriveAxis - SteerAxis) / magnitude));
    left1.set((-(DriveAxis + SteerAxis) / magnitude));
    left2.set((-(DriveAxis + SteerAxis) / magnitude));
  }
  
}

