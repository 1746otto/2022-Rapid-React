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

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem, double angle) {
    /*
     * addCommands(new ShooterHoodRetractCommand(hoodSubsystem) .andThen(
     */
    addCommands(
        /*
         * new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
         * .withTimeout(AutonConstants.kSpeedUpTime),
         */
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
                .withTimeout(AutonConstants.kShootTime)),
        /*
         * new DriveStraightCommand(driveSubsystem, 6.5, AutonConstants.kautonVelocity), new
         * SimpleAutonTurningCommand(driveSubsystem, pigeon, 30),
         */
        new ParallelRaceGroup(
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 8.5, AutonConstants.kautonVelocity),
            new IntakeCargoCommand(indexerSubsystem, intakeSubsystem)),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, 69.43),
        new ParallelRaceGroup(
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 9.83969,
                AutonConstants.kautonVelocity),
            new IntakeCargoCommand(indexerSubsystem, intakeSubsystem)),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, 4.833333333333,
            AutonConstants.kautonVelocity),
        new SimpleAutonTurningCommand(driveSubsystem, pigeon, -84),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, 9.3333333,
            AutonConstants.kautonVelocity),
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)));

  }


}
