package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;
import frc.robot.commands.DriveStraightPIDCommand;

public class TwoAndTwoAutonCommand extends SequentialCommandGroup {
  public TwoAndTwoAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem) {
    addCommands(
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(AutonConstants.kShootTime)),
        new ParallelRaceGroup(
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 8.5, AutonConstants.kautonVelocity),
            new IntakeCargoCommand(indexerSubsystem, intakeSubsystem))
                .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, -8.5,
                    AutonConstants.kautonVelocity).andThen(
                        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)))),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, -8.5, AutonConstants.kautonVelocity));
  }
}


