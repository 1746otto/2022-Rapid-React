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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeCargoCommand;
import frc.robot.Constants;
import frc.robot.Constants.AutonConstants;
import com.ctre.phoenix.sensors.PigeonIMU;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem) {
    /*
     * addCommands( new ParallelRaceGroup( new ShooterExponentialCommand(hoodSubsystem,
     * shooterSubsystem) .withTimeout(Constants.AutonConstants.kSpeedUpTime), new
     * IndexerFullForwardCommand(indexerSubsystem)) .andThen(new
     * DriveStraightPIDCommand(driveSubsystem, pigeon, 180.0 / 12.0, .3)) .andThen(new
     * SimpleAutonTurningCommand(driveSubsystem, pigeon, 16.89)) // left .andThen(new
     * ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem), new
     * IntakeExtendCommand(intakeSubsystem), new DriveStraightPIDCommand(driveSubsystem, pigeon,
     * 82.0 / 12, .3))) .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, -70.67)) //
     * right .andThen(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem), new
     * IntakeExtendCommand(intakeSubsystem), new DriveStraightPIDCommand(driveSubsystem, pigeon,
     * 97.83 / 12, .3))) .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 48.35)) //
     * left .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, -105.56 / 12, .3))
     * .andThen(new SimpleAutonTurningCommand(driveSubsystem, pigeon, 60.98)) // left .andThen(new
     * DriveStraightPIDCommand(driveSubsystem, pigeon, -18.0 / 12, .3)) .andThen(new
     * ParallelRaceGroup( new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
     * .withTimeout(Constants.AutonConstants.kSpeedUpTime * 3), new
     * IndexerFullForwardCommand(indexerSubsystem))))
     * 
     * ;
     */
    addCommands(

        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(AutonConstants.kShootTime)),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, 24.2 / 12.0,
            AutonConstants.kautonVelocity),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, 21),
        new ParallelRaceGroup(new IntakeCargoCommand(indexerSubsystem, intakeSubsystem),
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 78.0 / 12,
                AutonConstants.kautonVelocity)),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, -17.0 / 12.0,
            AutonConstants.kautonVelocity),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, -112.67),
        new ParallelRaceGroup(new IntakeCargoCommand(indexerSubsystem, intakeSubsystem),
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 112.3 / 12,
                AutonConstants.kautonVelocity)),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, 35.64),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, -96.87 / 12,
            AutonConstants.kautonVelocity),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, 77.03),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, -26.0 / 12,
            AutonConstants.kautonVelocity),
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(AutonConstants.kShootTime * 1.8)));
  }
}
