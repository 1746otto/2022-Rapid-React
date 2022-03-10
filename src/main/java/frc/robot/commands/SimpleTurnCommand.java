package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;
/*
public class SimpleTurnCommand extends CommandBase {
  
  private final DriveSubsystem m_drive;
  private final double m_rotation;
  private final double m_speed;
  private final int m_startingTicksLeft;
  private final int m_startingTicksRight;

  public SimpleTurnCommand(DriveSubsystem subsystem, double rotation, double speed) { 
    m_drive = subsystem;
    m_rotation = rotation;
    m_speed = speed;
    m_startingTicksLeft = m_drive.getLeftTicks();
    m_startingTicksRight = m_drive.getRightTicks();
    addRequirements(m_drive);
  }
  
    // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, m_rotation);
  }
  @Override
  public boolean isFinished() {
    return false;
  }
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }
}
*/