// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;


/** An example command that uses an example subsystem. */
public class TrajectoryAutonCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_drive;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TrajectoryAutonCommand(DriveSubsystem subsystem) {
        m_drive = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        DifferentialDriveVoltageConstraint autoVoltageConstraint =
                new DifferentialDriveVoltageConstraint(
                        new SimpleMotorFeedforward(DriveConstants.kVolts,
                                DriveConstants.kVoltSecondsPerMeter,
                                DriveConstants.kVoltSecondsSquaredPerMeter),
                        DriveConstants.kDriveKinematics, 10);
        TrajectoryConfig config = new TrajectoryConfig(DriveConstants.kMaxSpeedMetersPerSecond,
                DriveConstants.kMaxAccelerationMetersPerSecondPerSecond)
                        .setKinematics(DriveConstants.kDriveKinematics)
                        .addConstraint(autoVoltageConstraint);
        Trajectory newTrajectory =
                TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                        new Pose2d(3, 0, new Rotation2d(0)), config);
        RamseteCommand ramseteCommand = new RamseteCommand(newTrajectory, m_drive::getPose,
                new RamseteController(DriveConstants.kRamseteB, DriveConstants.kRamseteZeta),
                new SimpleMotorFeedforward(DriveConstants.kVolts,
                        DriveConstants.kVoltSecondsPerMeter,
                        DriveConstants.kVoltSecondsSquaredPerMeter),
                DriveConstants.kDriveKinematics, m_drive::getWheelSpeeds,
                new PIDController(DriveConstants.kPDriveVel, 0, 0),
                new PIDController(DriveConstants.kPDriveVel, 0, 0), m_drive::tankDriveVolts,
                m_drive);
        m_drive.resetOdometry(newTrajectory.getInitialPose());
        ramseteCommand.andThen(() -> m_drive.tankDriveVolts(0, 0));
    }
}
