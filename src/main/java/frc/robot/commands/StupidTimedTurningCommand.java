package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class StupidTimedTurningCommand extends CommandBase {
  public final DriveSubsystem m_drive;
  public final double m_time;
  public final Timer m_timer;
  public final double m_forward;
  public final double m_rotation;


  public StupidTimedTurningCommand(DriveSubsystem drive, double time, Timer timer, double forward,
      double rotation) {
    m_drive = drive;
    m_time = time;
    m_timer = timer;
    m_forward = forward;
    m_rotation = rotation;
  }

  @Override
  public void initialize() {
    m_timer.start();
    m_drive.arcadeDrive(m_forward, m_rotation);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
    m_timer.stop();
    m_timer.reset();
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= m_time;
  }
}
