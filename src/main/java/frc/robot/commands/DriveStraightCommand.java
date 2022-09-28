package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

/*
 * Ian your formatter is broken i can't use line breaks So here is the idea for smooth distance
 * drive. First we create a set of values that will let us accelerate at a safe rate. this can be
 * done in three easy steps 1. Convert distance to tics 2. Create safe acceleration value that
 * prevents the robot from slipping during speed. 3. Determine safe starting velocity that doesn't
 * slip when applied. 4. Take distance in tics divided by two and set that as the turning point. 5.
 * Apply a steady increase in velocity or voltage until we hit the point and flip the signed char to
 * be negative one and begin decreasing volatage until we hit the distance and stop(maybe). We will
 * have to define an ending velocity to reduce tic error and incase of a situation where we are
 * doing arced turn or something.
 * 
 * We will also need to devise a plan to let things happen inbetween the segments. And seeing as we
 * decided a turet was not necessary, we may need to figure out some leniency in the way we shoot.
 * Currently, I assume we will need a initial velocity input and final velocity/voltage thingy.
 */
public class DriveStraightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final double m_distance; // rotations
  private final double m_speed;
  private double m_startingRotations;

  /**
   * Creates a new TeleopDriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightCommand(DriveSubsystem subsystem, double distance /* feet */,
      double speed /* always positive */) {
    m_drive = subsystem;
    m_distance = (distance * 12 / Constants.DriveConstants.kwheelCircumfrence
        * Constants.DriveConstants.kmotorToWheelRatio); // feet
    m_speed = (distance >= 0 ? 1 : -1) * Math.abs(speed);

    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void initialize() {
    m_startingRotations = m_drive.getLeftRotations();
    System.out.println("StartRot" + m_startingRotations);
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);
    System.out.println("Left Ticks: " + m_drive.getLeftRotations());
    System.out.println("Right Ticks: " + m_drive.getRightRotations());
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (m_distance < 0) {
      if (m_distance >= (m_drive.getLeftRotations() - m_startingRotations)) {
        return true;
      }
    } else {
      if (m_distance <= (m_drive.getLeftRotations() - m_startingRotations)) {
        return true;
      }
    }
    return false;
  }
}
