package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCargoCommand extends ParallelRaceGroup{
    
    public IntakeCargoCommand(IndexerSubsystem indexerSubsystem,IntakeSubsystem intakeSubsystem){
        
        addCommands( new IntakeCommand(intakeSubsystem),
        new SequentialCommandGroup(
            new TopIndexerIntakeCommand(indexerSubsystem), 
            new BottomIndexerIntakeCommand(indexerSubsystem))); 
    }
}