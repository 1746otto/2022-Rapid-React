package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;



public class AutonDriveCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_rotation;
  private final double m_speed;

  public AutonDriveCommand(DriveSubsystem subsystem, double rotation, double speed) {
    m_drive = subsystem;
    m_rotation = rotation;
    m_speed = speed;
  }

  @Override
  public void execute() {
    m_drive.stupidArcadeDrive(m_speed, m_rotation);
  }

}
