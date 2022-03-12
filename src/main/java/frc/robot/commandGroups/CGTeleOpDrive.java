package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class CGTeleOpDrive {
    public static Command createDriveCommand(DriveSubsystem m_driveSubsystem,
            XboxController m_controller, ShooterSubsystem m_shooterSubsystem) {
        return new ArcadeDriveCommand(m_driveSubsystem, m_controller)
                .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem)
                        .withTimeout(Constants.AutonConstants.kShootTime))
                .andThen(new AutonDriveCommand(m_driveSubsystem, 0, .5)
                        .withTimeout(Constants.AutonConstants.kautonDriveTime));

    }
}
