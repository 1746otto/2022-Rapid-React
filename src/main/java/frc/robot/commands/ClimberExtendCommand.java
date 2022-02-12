package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberExtendCommand extends CommandBase {
    private ClimberSubsystem m_climber = new ClimberSubsystem();
    
    public ClimberExtendCommand(ClimberSubsystem subsystem) {
        m_climber = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {}
}
