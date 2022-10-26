package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class EmptyCommand extends CommandBase {
  public EmptyCommand() {}

  @Override
  public void initialize() {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
