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

public class TwoBallAutonCommand2 extends SequentialCommandGroup {
  public TwoBallAutonCommand2(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, Vision visionSubsystem,
      PigeonIMU pigeon, ShooterHoodSubsystem hoodSubsystem) {
    addCommands((new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem)
        .alongWith(new IndexerFullForwardCommand(indexerSubsystem))).andThen(
            /*
             * new DriveStraightCommand(driveSubsystem, 6.5, AutonConstants.kautonVelocity), new
             * SimpleAutonTurningCommand(driveSubsystem, pigeon, 30),
             */
            new ParallelRaceGroup(
                new DriveStraightPIDCommand(driveSubsystem, pigeon, 8.5,
                    AutonConstants.kautonVelocity),
                new IntakeCargoCommand(indexerSubsystem, intakeSubsystem)))
            .andThen(new DriveStraightPIDCommand(driveSubsystem, pigeon, -8.5,
                AutonConstants.kautonVelocity))
            .andThen(
                // new VisionDriveAutonCommand(driveSubsystem, visionSubsystem),
                // //temporary comment
                // out because we are not testin with vision
                new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
                    new ShooterExponentialCommand(hoodSubsystem, shooterSubsystem))));
  }
}


