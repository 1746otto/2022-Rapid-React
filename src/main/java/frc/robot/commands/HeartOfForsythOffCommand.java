package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;



public class HeartOfForsythOffCommand extends SequentialCommandGroup {
  public HeartOfForsythOffCommand(IndexerSubsystem indexerSubsystem,
      ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
    addCommands(new ParallelRaceGroup(new IndexerStopBothCommand(indexerSubsystem),
        new ShooterSetZeroPowerCommand(shooterSubsystem)));
  }
}
