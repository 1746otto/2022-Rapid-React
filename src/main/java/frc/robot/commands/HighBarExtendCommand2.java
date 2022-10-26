package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.commands.HighBarExtendCommand;
import frc.robot.commands.EmptyCommand;

public class HighBarExtendCommand2 extends SequentialCommandGroup {
  public HighBarExtendCommand2(ClimberSubsystem climber) {
    addCommands(new HighBarExtendCommand(climber).withTimeout(1.5),
        new HighBarRetractCommand(climber).withTimeout(0.5),
        new HighBarExtendCommand(climber).withTimeout(0.5)


    );
  }
}
