package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraightCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_drive;
    private final int m_distance; //tics
    private final double m_speed;
    private final int m_startingTics;
    /**
     * Creates a new TeleopDriveCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public DriveStraightCommand(DriveSubsystem subsystem, double distance /*feet*/, double speed) { 
      m_drive = subsystem;
      m_distance = (int)(distance * 12 / Constants.DriveConstants.kwheelCircumfrence * Constants.DriveConstants.kmotorToWheelRatio * Constants.DriveConstants.kticksPerRotation); //feet
      m_speed = (distance >= 0 ? 1 : -1) * speed;
      m_startingTics = m_drive.getLeftTicks();
      addRequirements(m_drive);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_drive.arcadeDrive(m_speed, 0);
    }

    public void end(boolean interrupted) {
      m_drive.arcadeDrive(0, 0);
    }

    public boolean isFinished() {
      if (m_distance < 0) {
        if (m_distance <= (m_startingTics - m_drive.getLeftTicks())) {
          return true;
        }
      }
      else {
        if (m_distance >= (m_drive.getLeftTicks() - m_startingTics)) {
          return true;
        }
      }
      return false;
    }
}
