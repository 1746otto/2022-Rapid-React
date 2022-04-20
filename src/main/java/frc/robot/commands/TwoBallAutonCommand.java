package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;

public class TwoBallAutonCommand extends SequentialCommandGroup {
  public TwoBallAutonCommand(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon) {
    addCommands(
        new ShooterCustomRPMCommand(shooterSubsystem, ShooterConstants.kHighGoalRPM)
            .withTimeout(AutonConstants.kSpeedUpTime),
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterCustomRPMCommand(shooterSubsystem, ShooterConstants.kHighGoalRPM)
                .withTimeout(AutonConstants.kShootTime)),

        new ParallelRaceGroup(
            new DriveStraightPIDCommand(driveSubsystem, pigeon, 9.5, AutonConstants.kautonVelocity),
            new IntakeCargoCommand(indexerSubsystem, intakeSubsystem)),
        new DriveStraightPIDCommand(driveSubsystem, pigeon, -9.5, AutonConstants.kautonVelocity),
        new ShooterCustomRPMCommand(shooterSubsystem, ShooterConstants.kHighGoalRPM)
            .withTimeout(AutonConstants.kSpeedUpTime),
        new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
            new ShooterCustomRPMCommand(shooterSubsystem, ShooterConstants.kHighGoalRPM)
                .withTimeout(AutonConstants.kShootTime)));
  }
}
