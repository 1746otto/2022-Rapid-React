package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutonBasic extends SequentialCommandGroup {
  public AutonBasic(DriveSubsystem drive) {
    addCommands(
        new TimedDrive(drive, AutonConstants.kautonVelocity, AutonConstants.kautonDriveTime));
  }
}
