package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;



public class HeartOfForsyth extends SequentialCommandGroup {
  public HeartOfForsyth(IndexerSubsystem indexerSubsystem, ShooterSubsystem shooterSubsystem,
      IntakeSubsystem intakeSubsystem) {
    addCommands(new ParallelRaceGroup(new IndexerFullForwardCommand(indexerSubsystem),
        new ShooterFullPowerCommand(shooterSubsystem),
        new IntakeFullPowerCommand(intakeSubsystem)));
  }
}


