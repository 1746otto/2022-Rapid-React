package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;

public class FullAutonCommand extends SequentialCommandGroup {
  public FullAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem) {
    addCommands(new SequentialCommandGroup(
        new OneBallAutonCommand(indexerSubsystem, shooterSubsystem, driveSubsystem, hoodSubsystem,
            pigeon, intakeSubsystem),
        new TwoBallAutonCommand(indexerSubsystem, intakeSubsystem, shooterSubsystem, driveSubsystem,
            visionSubsystem, pigeon)));
  }
}
