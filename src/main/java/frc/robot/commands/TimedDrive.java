package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TimedDrive extends CommandBase{
  private final DriveSubsystem m_driveSubsystem;
  private final double m_velocity;
  private final double m_time;
  private double m_endTime;
  public TimedDrive(DriveSubsystem subsystem, double velocity, double time) {
    m_driveSubsystem = subsystem;
    m_time = time;
    m_velocity = velocity;
    addRequirements(m_driveSubsystem);
  }
  @Override
  public void initialize() {
    m_driveSubsystem.arcadeDrive(m_velocity, 0);
    m_endTime = Timer.getFPGATimestamp() + m_time;
  }
    
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stop();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= m_endTime;
  }
  
}
