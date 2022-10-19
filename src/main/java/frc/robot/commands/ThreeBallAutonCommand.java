package frc.robot.commands;

import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.commands.SimpleAutonTurningCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.IntakeCargoCommand;
import frc.robot.Constants;
import com.ctre.phoenix.sensors.PigeonIMU;

public class ThreeBallAutonCommand extends ParallelRaceGroup {
  public ThreeBallAutonCommand(ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem,
      IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem, PigeonIMU pigeon) {

    addCommands(
        new ParallelRaceGroup(
            new ShooterFullPowerCommand(shooterSubsystem)
                .withTimeout(Constants.AutonConstants.kSpeedUpTime),
            new IndexerFullForwardCommand(indexerSubsystem))
                .andThen(new DriveStraightCommand(driveSubsystem, 18.0 / 12, .3))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 16.89)) // left
                .andThen(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                    new IntakeExtendCommand(intakeSubsystem),
                    new DriveStraightCommand(driveSubsystem, 82.0 / 12, .3)))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, -70.67)) // right
                .andThen(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                    new IntakeExtendCommand(intakeSubsystem),
                    new DriveStraightCommand(driveSubsystem, 97.83 / 12, .3)))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 48.35)) // left
                .andThen(new DriveStraightCommand(driveSubsystem, -105.56 / 12, .3))
                .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 60.98)) // left
                .andThen(new DriveStraightCommand(driveSubsystem, -18.0 / 12, .3))
                .andThen(new ParallelRaceGroup(
                    new ShooterFullPowerCommand(shooterSubsystem)
                        .withTimeout(Constants.AutonConstants.kSpeedUpTime * 3),
                    new IndexerFullForwardCommand(indexerSubsystem))))

    ;
  }
}
