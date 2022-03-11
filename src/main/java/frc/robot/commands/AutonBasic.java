package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutonBasic extends SequentialCommandGroup {
  public AutonBasic(DriveSubsystem drive) {
    addCommands(
        // new TimedDrive(drive, AutonConstants.kautonVelocity, AutonConstants.kautonDriveTime));
        new SimpleAutonTurningCommand(drive, new PigeonIMU(DriveConstants.kPigeon), 90));
  }
}
