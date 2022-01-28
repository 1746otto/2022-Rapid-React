package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.AutonConstants;

public class DriveForward extends CommandBase{
  private final DriveSubsystem m_driveSubsystem;
  private final double m_velocity = 1.0;
  private double m_endTime;
  public DriveForward(DriveSubsystem subsystem) {
    m_driveSubsystem = subsystem;
    addRequirements(m_driveSubsystem);
  }
  @Override
  public void initialize() {
    m_driveSubsystem.driveForward();
  }
    
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

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
