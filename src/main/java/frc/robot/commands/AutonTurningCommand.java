package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutonTurningCommand extends CommandBase {

  public final DriveSubsystem m_driveSubsystem;
  public final PigeonIMU m_pigeon;
  public final double m_angle;
  public boolean m_passedFinish;
  public final double kp = 1 / 180;

  public double heading;

  public double error;


  public AutonTurningCommand(DriveSubsystem driveSubsystem, PigeonIMU pigeon, double angle) {
    m_driveSubsystem = driveSubsystem;
    m_pigeon = pigeon;
    m_angle = angle;
  }

  @Override
  public void initialize() {
    m_pigeon.setFusedHeading(0);
    m_driveSubsystem.arcadeDrive(0.1, 0.1);
  }

  // look over this code again it is probably wrong
  @Override
  public void execute() {
    heading = m_pigeon.getFusedHeading();
    heading = heading % 360;
    if (heading > 180)
      heading -= 360;
    if (heading < -180)
      heading += 360;
    error = m_angle - heading;
    m_driveSubsystem.arcadeDrive(0.15, kp * error);
    System.out.println("bruh " + (kp * error));
  }


  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.arcadeDrive(0, 0);
    m_pigeon.setFusedHeading(0);
  }


  @Override
  public boolean isFinished() {
    heading = heading % 360;
    if (heading > 180)
      heading -= 360;
    if (heading < 180)
      heading += 360;
    if (m_angle >= 0) {
      return heading >= m_angle;
    } else {
      return heading <= m_angle;
    }
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
