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
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.OneBallAutonCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ShooterCustomRPMCommand;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.ClimberExtendCommand;
import frc.robot.commands.ClimberRetractCommand;
import frc.robot.commands.ClimberStopCommand;
import frc.robot.commands.HighBarExtendCommand;
import frc.robot.commands.ReleaseMidBarHook;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.commands.IndexerUpperCommand;
import frc.robot.commands.IntakeCargoCommand;
import frc.robot.commands.LowGoalCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.commands.ShooterHighLowCommand;
import frc.robot.commands.ShooterHoodExtendCommand;
import frc.robot.commands.ShooterHoodRetractCommand;
import frc.robot.commands.ShooterHoodToggleCommand;
import frc.robot.commands.VisionDriveCommand;
import frc.robot.commands.VisionTuningCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterHoodSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 * 
 * 
 * By default, the code will connect to 2 color sensors. For both sensors, Power is pin 36 and the
 * easiest ground near that is pin 38.
 * 
 * Connect the first sensor's SDA to pin 6, and SCL to pin 7.
 * 
 * Connect the 2nd sensor's SDA to pin 9, and SCL to pin 10.
 * 
 * The roboRIO can provide Ground, 5v and the communication signal to the device.
 * 
 * 5v on the roboRIO is MXP pin 1 (Top right pin), and connects to pin 39 of the Pi Pico. Gnd on the
 * roboRIO is pin 8 (bottom row 4th in from the right) and connects to either Pin 3 or Pin 38 of the
 * Pi Pico. UART Rx on the roboRIO is pin 10 (bottom row 5th in from the right) and connects to pin
 * 1 of the Pi Pico.
 */
public class RobotContainer {
    private final XboxController m_controller = new XboxController(ControllerConstants.kport);
    private final XboxController m_controller2 = new XboxController(ControllerConstants.kport2);
    // The robot's subsystems and commands are defined here...
    private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
    private final ShooterHoodSubsystem m_shooterHoodSubsystem = new ShooterHoodSubsystem();
    private final Vision m_visionSubsystem = new Vision();
    private final VisionDriveCommand m_visionDriveCommand =
            new VisionDriveCommand(m_driveSubsystem, m_controller, m_visionSubsystem);
    private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
    private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
    private final ColorSensorSubsystem m_colorSensorSubsystem = new ColorSensorSubsystem();

    private final Compressor compressor =
            new Compressor(RobotConstants.kREVPH, PneumaticsModuleType.REVPH);
    private final VisionTuningCommand m_visionTuningCommand =
            new VisionTuningCommand(m_visionSubsystem, m_driveSubsystem);
    private final ShooterCustomRPMCommand m_customRPMCommand =
            new ShooterCustomRPMCommand(m_shooterSubsystem, ShooterConstants.kHighGoalRPM);
    private final ShooterFullPowerCommand m_shooterFullPower =
            new ShooterFullPowerCommand(m_shooterSubsystem);
    private final LowGoalCommand m_lowGoalCommand = new LowGoalCommand(m_shooterSubsystem);
    private final ShooterHoodExtendCommand m_shooterHoodExtendCommand =
            new ShooterHoodExtendCommand(m_shooterHoodSubsystem);
    private final ShooterHoodRetractCommand m_shooterHoodRetractCommand =
            new ShooterHoodRetractCommand(m_shooterHoodSubsystem);
    private final ShooterHighLowCommand m_shooterHighLowCommand =
            new ShooterHighLowCommand(m_shooterHoodSubsystem, m_shooterSubsystem);


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
        JoystickButton xBoxY2 = new JoystickButton(m_controller2, XboxController.Button.kY.value);
        JoystickButton xBoxA2 = new JoystickButton(m_controller2, XboxController.Button.kA.value);
        JoystickButton xBoxB2 = new JoystickButton(m_controller2, XboxController.Button.kB.value);
        JoystickButton xBoxSelect2 =
                new JoystickButton(m_controller2, XboxController.Button.kStart.value);
        JoystickButton xBoxY = new JoystickButton(m_controller, XboxController.Button.kY.value);
        JoystickButton xBoxB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton xBoxX = new JoystickButton(m_controller, XboxController.Button.kX.value);
        JoystickButton xBoxA = new JoystickButton(m_controller, XboxController.Button.kA.value);
        JoystickButton xBoxStart =
                new JoystickButton(m_controller, XboxController.Button.kStart.value);
        JoystickButton xBoxX2 = new JoystickButton(m_controller2, XboxController.Button.kX.value);
        JoystickButton xBoxLBumper =
                new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
        JoystickButton xBoxLBumper2 =
                new JoystickButton(m_controller2, XboxController.Button.kLeftBumper.value);
        JoystickButton xBoxRBumper2 =
                new JoystickButton(m_controller2, XboxController.Button.kRightBumper.value);


        xBoxY2.whenPressed(new ClimberExtendCommand(m_climberSubsystem));
        xBoxA2.whenPressed(new ClimberRetractCommand(m_climberSubsystem));
        xBoxX2.whenPressed(new HighBarExtendCommand(m_climberSubsystem));
        xBoxB2.whenHeld(new ReleaseMidBarHook(m_climberSubsystem));
        xBoxSelect2.whenPressed(new ClimberStopCommand(m_climberSubsystem));
        xBoxX.whenHeld(new VisionDriveCommand(m_driveSubsystem, m_controller, m_visionSubsystem));
        xBoxStart.whenHeld(new VisionTuningCommand(m_visionTuningCommand));
        xBoxA.toggleWhenPressed(new IntakeCargoCommand(m_indexerSubsystem, m_intakeSubsystem));
        xBoxRBumper2.whenPressed(new ShooterHoodToggleCommand(m_shooterHoodSubsystem));

        /*
         * xBoxLBumper.whenHeld(m_customRPMCommand.withTimeout(Constants.AutonConstants.
         * kSpeedUpTime) .andThen(new IndexerUpperCommand(m_indexerSubsystem)
         * .withTimeout(IndexerConstants.kTwoBallDelay) .raceWith( new
         * ShooterCustomRPMCommand(m_shooterSubsystem, ShooterConstants.kHighGoalRPM)) .andThen(new
         * IndexerFullForwardCommand(m_indexerSubsystem).raceWith( new
         * ShooterCustomRPMCommand(m_shooterSubsystem, ShooterConstants.kHighGoalRPM)))));
         */

        /**
         *
         */
        xBoxLBumper
                .whenHeld(m_shooterHighLowCommand.withTimeout(Constants.AutonConstants.kSpeedUpTime)
                        .andThen(new IndexerUpperCommand(m_indexerSubsystem)
                                .withTimeout(IndexerConstants.kTwoBallDelay)
                                .raceWith(new ShooterHighLowCommand(m_shooterHoodSubsystem,
                                        m_shooterSubsystem))
                                .andThen(new IndexerFullForwardCommand(m_indexerSubsystem)
                                        .raceWith(new ShooterHighLowCommand(m_shooterHoodSubsystem,
                                                m_shooterSubsystem)))));
        xBoxLBumper2.whenHeld(new OuttakeCommand(m_indexerSubsystem, m_intakeSubsystem));


    }

    // Constants.ShooterConstants.kFullPower - = 1
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
    public Command getAutonomousCommand() {
        return new OneBallAutonCommand(m_indexerSubsystem, m_shooterSubsystem, m_driveSubsystem);
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
