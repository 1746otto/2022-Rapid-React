package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutonConstants;
import frc.robot.subsystems.DriveSubsystem;

public class TwoBallAuton extends SequentialCommandGroup {
  public TwoBallAuton(DriveSubsystem drive) {
    addCommands();
  }
}
