package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class CGAuto {
    public static Command createAutoCommand(ShooterSubsystem m_shooterSubsystem,
            IndexerSubsystem m_indexerSubsystem, DriveSubsystem m_driveSubsystem) {
        return new ShooterFullPowerCommand(m_shooterSubsystem)
                .withTimeout(Constants.AutonConstants.kSpeedUpTime)
                .andThen(new IndexerFullForwardCommand(m_indexerSubsystem)
                        .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem)
                                .withTimeout(Constants.AutonConstants.kShootTime)))
                .andThen(new AutonDriveCommand(m_driveSubsystem, 0, .5)
                        .withTimeout(Constants.AutonConstants.kautonDriveTime));

    }
}
