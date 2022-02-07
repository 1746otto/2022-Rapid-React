// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonBasic;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.VisionDriveCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller = new XboxController(ControllerConstants.kport);
  private final JoystickButton m_visionDriveJoystickButton = new JoystickButton(m_controller, XboxController.Button.kA.value);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final Vision m_visionSubsystem = new Vision();
  private final VisionDriveCommand m_visionDriveCommand = new VisionDriveCommand(m_driveSubsystem, m_controller, m_visionSubsystem);
  

  private final AutonBasic m_autoCommand = new AutonBasic(m_driveSubsystem);
  //private final ShooterCommand m_autoCommand = new ShooterCommand(m_shooterSubsystem);
  private final ArcadeDriveCommand m_arcadeDriveCommand = new ArcadeDriveCommand(m_driveSubsystem, m_controller);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_visionDriveJoystickButton.whenPressed(m_visionDriveCommand).whenReleased(m_arcadeDriveCommand);
    //TODO: bind an xbox button to the shoot command    ` 
  }
  private void configureDefaultCommands() {
    m_driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(m_driveSubsystem, m_controller));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public Command getTeleopDrive() {
    return m_arcadeDriveCommand;
  }
}
