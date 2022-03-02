// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ExampleCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller = new XboxController(ControllerConstants.kport);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final DriveStraightCommand m_driveStraightCommand = new DriveStraightCommand(m_driveSubsystem, Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed);
  private final ExampleCommand m_nothingCommand = new ExampleCommand();
  private final Command m_tarmacAuton = new AutonDriveCommand(m_driveSubsystem, 0 ,Constants.AutonConstants.kautonSpeedBackwards).withTimeout(Constants.AutonConstants.kautonDriveTime);
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
    //TODO: bind an xbox button to the shoot command
  }
  private void configureDefaultCommands() {
    m_driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(m_driveSubsystem, m_controller));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command createAutoCommand() {
    return new DriveStraightCommand(m_driveSubsystem, Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed)
      .andThen(new DriveStraightCommand(m_driveSubsystem, -1 * Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed))
      .andThen(new DriveStraightCommand(m_driveSubsystem, Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed))
      .andThen(new DriveStraightCommand(m_driveSubsystem, -1 * Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed))
      .andThen(new DriveStraightCommand(m_driveSubsystem, Constants.DriveConstants.kautonDistance, Constants.DriveConstants.kautonSpeed));
  }
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return createAutoCommand();
  }
  
  public Command getTeleopDrive() {
    return m_arcadeDriveCommand;
  }
}
