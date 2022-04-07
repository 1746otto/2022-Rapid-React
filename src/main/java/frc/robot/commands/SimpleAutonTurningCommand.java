package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class SimpleAutonTurningCommand extends CommandBase {

  public final DriveSubsystem m_driveSubsystem;
  public final PigeonIMU m_pigeon;
  public final double m_angle;
  public boolean m_passedFinish;
  public double error = 0;
  public final double kpGain = (2 / 360.0);


  public SimpleAutonTurningCommand(DriveSubsystem driveSubsystem, PigeonIMU pigeon, double angle) {
    m_driveSubsystem = driveSubsystem;
    m_pigeon = pigeon;
    m_angle = angle;
    addRequirements(m_driveSubsystem);

  }

  @Override
  public void initialize() {
    // m_pigeon.setYaw(0);
    m_pigeon.setFusedHeading(0);
    m_driveSubsystem.stupidArcadeDrive(0, 1);

    // m_driveSubsystem.arcadeDrive(0, (m_angle < 0 ? -1 : 1) * DriveConstants.kSafeTurnSpeed);
  }

  // look over this code again it is probably wrong

  @Override
  public void execute() {

    // m_driveSubsystem.stupidArcadeDrive(0, 1);

    /*
     * if (m_passedFinish) { m_driveSubsystem.arcadeDrive(0, (m_angle - m_pigeon.getFusedHeading())
     * 360 * DriveConstants.kSafeTurnSpeed + DriveConstants.kSafeTurnSpeed);
     * 
     * } else { if (m_angle < 0) { if (m_pigeon.getFusedHeading() >= m_angle) { m_passedFinish =
     * true; } else { m_driveSubsystem.arcadeDrive(0, -1 * ((m_angle - m_pigeon.getFusedHeading()) /
     * 360 * DriveConstants.kSafeTurnSpeed + DriveConstants.kSafeTurnSpeed)); } } else if
     * (m_pigeon.getFusedHeading() >= m_angle) { m_passedFinish = true; } else {
     * m_driveSubsystem.arcadeDrive(0, (m_angle - m_pigeon.getFusedHeading()) / 360 *
     * DriveConstants.kSafeTurnSpeed + DriveConstants.kSafeTurnSpeed); } }
     */

  }

  @Override
  public void end(boolean interrupted) {
    // m_driveSubsystem.arcadeDrive(0, 0);
    m_pigeon.setFusedHeading(0);
  }

  @Override
  public boolean isFinished() {

    return false;
  }



}
/*
 * For turning advanced we can get our final angle and then use the pigeon to get the angle at the
 * start of the turn and then we use the arpit to calculate the distance one side has to travel
 * greater than the other. Then use PID(F) to do the make sure the turning is on track by getting
 * the total diff in rot of the two sides of the drive train and dividing it by theta over 360 times
 * full circle rotations. We use the number we get from those calculations to compare with
 * angle/currentAngle and if they are about the same, then they good.
 */
