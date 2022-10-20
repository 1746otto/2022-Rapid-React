package frc.robot.commands;

import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.commands.ShooterExponentialCommand;
import frc.robot.commands.SimpleAutonTurningCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.IntakeCargoCommand;
import frc.robot.Constants;
import com.ctre.phoenix.sensors.PigeonIMU;

public class ThreeBallAutonCommand extends ParallelRaceGroup {
  public ThreeBallAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem) {

    addCommands(
        new ParallelRaceGroup(
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(Constants.AutonConstants.kSpeedUpTime),
            new IndexerFullForwardCommand(indexerSubsystem))
                .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, 180.0 / 12.0, .3))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 16.89)) // left
                .andThen(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                    new IntakeExtendCommand(intakeSubsystem),
                    new DriveStraightPIDCommand(driveSubsystem, pigeon, 82.0 / 12, .3)))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, -70.67)) // right
                .andThen(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                    new IntakeExtendCommand(intakeSubsystem),
                    new DriveStraightPIDCommand(driveSubsystem, pigeon, 97.83 / 12, .3)))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 48.35)) // left
                .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, -105.56 / 12, .3))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 60.98)) // left
                .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, -18.0 / 12, .3))
                .andThen(new ParallelRaceGroup(
                    new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                        .withTimeout(Constants.AutonConstants.kSpeedUpTime * 3),
                    new IndexerFullForwardCommand(indexerSubsystem))))

    ;
  }
}
