// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import org.ejml.equation.IntegerSequence.Explicit;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Vision;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.RobotConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


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


  }

  // Constants.ShooterConstants.kFullPower - = 1
  private void configureDefaultCommands() {

  }

  public void enableCompressor() {

  }

  public void disableCompressor() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;

  }

  /*
   * public Command createAutoCommand() { return new ShooterFullPowerCommand(m_shooterSubsystem)
   * .withTimeout(Constants.AutonConstants.kSpeedUpTime) .andThen(new
   * IndexerFullForwardCommand(m_indexerSubsystem) .andThen(new
   * IntakeExtendCommand(m_intakeSubsystem) .raceWith(new DriveStraightCommand(m_driveSubsystem,
   * AutonConstants.kDistanceToBall, AutonConstants.kautonVelocity) .andThen(new
   * DriveStraightCommand(m_driveSubsystem, AutonConstants.kDistanceToBall,
   * AutonConstants.kautonVelocity)) .andThen(new ShooterFullPowerCommand(m_shooterSubsystem)
   * .withTimeout(Constants.AutonConstants.kSpeedUpTime) .andThen(new
   * IndexerFullForwardCommand(m_indexerSubsystem) .raceWith(new
   * ShooterFullPowerCommand(m_shooterSubsystem))))))); }
   */

  public Command createAutoCommand() {
    return null;

  }


  public Command getTeleopDrive() {
    return null;

  }
}
