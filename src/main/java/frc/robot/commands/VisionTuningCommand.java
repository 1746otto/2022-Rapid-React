package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Vision;

public class VisionTuningCommand extends CommandBase {
  VisionDriveCommand tunedCommand;

  public VisionTuningCommand(VisionTuningCommand m_visionTuningCommand) {
    tunedCommand = m_visionTuningCommand;
  }

  public VisionTuningCommand(Vision m_visionSubsystem, DriveSubsystem m_driveSubsystem) {}

  @Override
  public void execute() {
    tunedCommand.kP = SmartDashboard.getNumber("kP", tunedCommand.kP);
    tunedCommand.kD = SmartDashboard.getNumber("kD", tunedCommand.kD);
    SmartDashboard.putNumber("kP", tunedCommand.kP);
    SmartDashboard.putNumber("kD", tunedCommand.kD);
    SmartDashboard.putNumber("error", tunedCommand.error);
    SmartDashboard.putNumber("delta error", tunedCommand.deltaError);
    SmartDashboard.putNumber("previous error", tunedCommand.prevError);
    SmartDashboard.putNumber("rotational signal", tunedCommand.rotationSignal);
    SmartDashboard.updateValues();
  }
}
