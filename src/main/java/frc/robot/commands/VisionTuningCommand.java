package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Vision;

public class VisionTuningCommand extends CommandBase {
  VisionTuningCommand tunedCommand;

  public VisionTuningCommand(VisionTuningCommand m_visionTuningCommand) {
    tunedCommand = m_visionTuningCommand;
  }

  public VisionTuningCommand(Vision m_visionSubsystem, DriveSubsystem m_driveSubsystem) {}

  @Override
  public void execute() {

  }
}
