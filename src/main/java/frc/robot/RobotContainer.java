// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.AutonConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.AutonBasic;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  // private final AutonBasic m_autoCommand = new AutonBasic(m_driveSubsystem);
  //private final ShooterCommand m_autoCommand = new ShooterCommand(m_shooterSubsystem);
 // private final IndexerCommand m_autoCommand = new IndexerCommand(m_indexerSubsystem);
 
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

  }
  
      
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //TODO: bind an xbox button to the shoot command
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return createAutoCommand();
   
  
  }
  public Command createAutoCommand () {
    return new ShooterFullPowerCommand(m_shooterSubsystem).withTimeout(AutonConstants.kSpeedUpTime)
      .andThen(new ShooterFullPowerCommand(m_shooterSubsystem)
        .alongWith(new IndexerFullForwardCommand(m_indexerSubsystem))
        .withTimeout(AutonConstants.kShootTime))
      .andThen(new ArcadeDriveCommand(m_driveSubsystem).withTimeout(AutonConstants.kautonDriveTime));
  }
}
