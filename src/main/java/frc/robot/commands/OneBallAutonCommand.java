package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;



public class OneBallAutonCommand extends SequentialCommandGroup {
  public OneBallAutonCommand(IndexerSubsystem indexerSubsystem, ShooterSubsystem shooterSubsystem,
      DriveSubsystem driveSubsystem, ShooterHoodSubsystem hoodSubsystem,
      IntakeSubsystem intakeSubsystem) {
    addCommands(
        new ShooterHighLowCommand(hoodSubsystem, shooterSubsystem)
            .withTimeout(AutonConstants.kSpeedUpTime),
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterHighLowCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(AutonConstants.kShootTime)),
        new TimedDrive(driveSubsystem, AutonConstants.kautonVelocity,
            AutonConstants.kautonDriveTime)
                .alongWith(new IntakeCargoCommand(indexerSubsystem, intakeSubsystem)));
  }
}
