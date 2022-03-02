package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraightCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_drive;
    private final int m_distance; //ticks
    private final double m_speed;
    private final int m_startingTicks;
    /**
     * Creates a new TeleopDriveCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public DriveStraightCommand(DriveSubsystem subsystem, double distance /*feet*/, double speed /*always positive*/) { 
      m_drive = subsystem;
      m_distance = (int)(distance * 12 / Constants.DriveConstants.kwheelCircumfrence * Constants.DriveConstants.kmotorToWheelRatio * Constants.DriveConstants.kticksPerRotation); //feet
      m_speed = (distance >= 0 ? 1 : -1) * speed;
      m_startingTicks = m_drive.getLeftTicks();
      addRequirements(m_drive);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void initialize() {
      m_drive.arcadeDrive(m_speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
      m_drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
      if (m_distance < 0) {
        if (m_distance >= (m_drive.getLeftTicks() - m_startingTicks)) { 
          return true;
        }
      }
      else {
        if (m_distance <= (m_drive.getLeftTicks() - m_startingTicks)) {
          return true;
        }
      }
      return false;
    }
}
