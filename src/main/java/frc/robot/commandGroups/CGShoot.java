package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.IndexerFullForwardCommand;
import frc.robot.commands.ShooterFullPowerCommand;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class CGShoot {
    public static Command createShootCommand(ShooterSubsystem m_shooterSubsystem,
            IndexerSubsystem m_indexerSubsystem) {
        return new ShooterFullPowerCommand(m_shooterSubsystem)
                .withTimeout(Constants.AutonConstants.kSpeedUpTime)
                .andThen(new IndexerFullForwardCommand(m_indexerSubsystem)
                        .raceWith(new ShooterFullPowerCommand(m_shooterSubsystem)));
    }
}
