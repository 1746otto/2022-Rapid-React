package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class SimpleAutonTurningCommand extends CommandBase {

  public final DriveSubsystem m_driveSubsystem;
  public final PigeonIMU m_pigeon;
  public final double m_angle;
  public double[] array;


  public SimpleAutonTurningCommand(DriveSubsystem driveSubsystem, PigeonIMU pigeon, double angle) {
    m_driveSubsystem = driveSubsystem;
    m_pigeon = pigeon;
    m_angle = Math.abs(angle);
  }

  @Override
  public void initialize() {
    //m_pigeon.setYaw(m_pigeon.compa)

    // m_pigeon.setYaw(0);

    // m_driveSubsystem.arcadeDrive(0, DriveConstants.kSafeTurnSpeed);
  }

  @Override
  public void execute() {
    m_pigeon.getYawPitchRoll(array);
    if (m_pigeon.getState().Ready.value == 1) {
      System.out.println("invalid state");
    }
    System.out.println("Yaw is " + array[0]);
    System.out.println("Roll is " + array[2]);
    System.out.println("Pitch is " + array[1]);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.arcadeDrive(0, 0);
    m_pigeon.setYaw(0);
  }

  /*
   * @Override public boolean isFinished() { return m_pigeon.getYaw() >= m_angle; }
   */

}
