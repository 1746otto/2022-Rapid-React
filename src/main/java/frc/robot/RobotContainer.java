// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.AutonBasic;
import frc.robot.commands.ClimberExtendCommand;
import frc.robot.commands.ClimberRetractCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.commands.ShooterTuning2Command;
import frc.robot.commands.ShooterTuningCommand;
import frc.robot.commands.VisionDriveCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;
import frc.robot.Constants.ControllerConstants;

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
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final Vision m_visionSubsystem = new Vision();
  private final VisionDriveCommand m_visionDriveCommand =
      new VisionDriveCommand(m_driveSubsystem, m_controller, m_visionSubsystem);
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  private final Command m_tarmacAuton =
      new AutonDriveCommand(m_driveSubsystem, 0, Constants.AutonConstants.kautonSpeedBackwards)
          .withTimeout(Constants.AutonConstants.kautonDriveTime);
  // private final ShooterCommand m_autoCommand = new
  // ShooterCommand(m_shooterSubsystem);
  private final ArcadeDriveCommand m_arcadeDriveCommand =
      new ArcadeDriveCommand(m_driveSubsystem, m_controller);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_controller, XboxController.Button.kA.value)
        .whenPressed(m_visionDriveCommand).whenReleased(m_arcadeDriveCommand);

    JoystickButton m_DriverLBumper =
        new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
    m_DriverLBumper.whenHeld(new ShooterFullPowerCommand(m_shooterSubsystem));

    JoystickButton m_climbJoystickButton =
        new JoystickButton(m_controller, XboxController.Button.kY.value);
    m_climbJoystickButton.whenPressed(new ClimberExtendCommand(m_climberSubsystem));

    JoystickButton m_DriverRBumper =
        new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
    m_DriverRBumper.whenHeld(new IndexerFullForwardCommand(m_indexerSubsystem));

    JoystickButton m_XButton = new JoystickButton(m_controller, XboxController.Button.kX.value);
    m_XButton.whenHeld(new ShooterTuningCommand(m_shooterSubsystem));

    JoystickButton m_AButton = new JoystickButton(m_controller, XboxController.Button.kX.value);
    m_AButton.whenHeld(new ShooterTuning2Command(m_shooterSubsystem));
  }

  // Constants.ShooterConstants.kFullPower - = 1
  private void configureDefaultCommands() {
    m_driveSubsystem.setDefaultCommand(new RunCommand(() -> m_driveSubsystem.arcadeDrive(
        m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
        m_controller.getLeftX()), m_driveSubsystem));
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

  public Command createAutoCommand() {
    return new ShooterFullPowerCommand(m_shooterSubsystem)
        .withTimeout(Constants.AutonConstants.kSpeedUpTime)
        .andThen(new IndexerFullForwardCommand(m_indexerSubsystem)
            .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem)
                .withTimeout(Constants.AutonConstants.kShootTime)))
        .andThen(new AutonDriveCommand(m_driveSubsystem, 0, .5)
            .withTimeout(Constants.AutonConstants.kautonDriveTime));
  }

  public Command getTeleopDrive() {
    return m_arcadeDriveCommand;
  }
}
