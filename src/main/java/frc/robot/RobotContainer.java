// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.Constants.RobotConstants;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.BottomIndexerIntakeCommand;
import frc.robot.commands.ClimberExtendCommand;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.commands.IntakeCargoCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OneBallAutonCommand;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.commands.TopIndexerIntakeCommand;
import frc.robot.commands.VisionDriveCommand;
import frc.robot.commands.VisionTuningCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;

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
  private final Compressor compressor =
      new Compressor(RobotConstants.kREVPH, PneumaticsModuleType.REVPH);
  private final VisionTuningCommand m_visionTuningCommand =
      new VisionTuningCommand(m_visionSubsystem, m_driveSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    disableCompressor();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton xBoxY = new JoystickButton(m_controller, XboxController.Button.kY.value);
    JoystickButton xBoxB = new JoystickButton(m_controller, XboxController.Button.kB.value);
    JoystickButton xBoxX = new JoystickButton(m_controller, XboxController.Button.kX.value);
    JoystickButton xBoxA = new JoystickButton(m_controller, XboxController.Button.kA.value);
    JoystickButton xBoxStart = new JoystickButton(m_controller, XboxController.Button.kStart.value);
    JoystickButton xBoxLBumper =
        new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);

    xBoxY.whenPressed(new ClimberExtendCommand(m_climberSubsystem));
    xBoxX.whenHeld(new VisionDriveCommand(m_driveSubsystem, m_controller, m_visionSubsystem));
    xBoxStart.whenHeld(new VisionTuningCommand(m_visionTuningCommand));
    xBoxA.toggleWhenPressed(new IntakeCargoCommand(m_indexerSubsystem, m_intakeSubsystem));
    xBoxLBumper.whenHeld(new ShooterFullPowerCommand(m_shooterSubsystem)
        .withTimeout(Constants.AutonConstants.kSpeedUpTime)
        .andThen(new IndexerFullForwardCommand(m_indexerSubsystem)
            .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem))));
    xBoxB.toggleWhenPressed(new StartEndCommand(m_intakeSubsystem::extend,
        m_intakeSubsystem::turnOffIntake, m_intakeSubsystem));
  }

  private void configureDefaultCommands() {
    m_driveSubsystem.setDefaultCommand(new RunCommand(() -> m_driveSubsystem.arcadeDrive(
        m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis(),
        m_controller.getLeftX()), m_driveSubsystem));
  }

  public void enableCompressor() {
    compressor.enableDigital();
  }

  public void disableCompressor() {
    compressor.disable();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new OneBallAutonCommand(m_indexerSubsystem, m_shooterSubsystem, m_driveSubsystem);
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
    return new ArcadeDriveCommand(m_driveSubsystem, m_controller)
        .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem)
            .withTimeout(Constants.AutonConstants.kShootTime))
        .andThen(new AutonDriveCommand(m_driveSubsystem, 0, .5)
            .withTimeout(Constants.AutonConstants.kautonDriveTime));
  }
}
