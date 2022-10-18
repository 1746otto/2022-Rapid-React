package frc.robot.subsystems;

import frc.robot.commands.ShooterFullPowerCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.IntakeCargoCommand;

public class ThreeBallAutonCommand extends ParallelRaceGroup {
  public ThreeBallAutonCommand(ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem, IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem) {
    addCommands(
    new ShooterFullPowerCommand(shooterSubsystem),
    
    );
  }
}
