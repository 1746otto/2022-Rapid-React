package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
  public final ShooterSubsystem m_shooter;

  public ShooterCommand(ShooterSubsystem shooterSubsystem) {
    m_shooter = shooterSubsystem;
  }

  @Override
  public void initialize() {
    m_shooter.highGoalShooter();
  }


  public void end() {
    m_shooter.setZeroPower();
  }

  public boolean isFinished() {
    return false;
  }
}
