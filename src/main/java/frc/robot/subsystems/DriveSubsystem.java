package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CANIDConstants;
import frc.robot.Constants.ControllerConstants;

public class DriveSubsystem extends SubsystemBase {

    private final CANSparkMax m_rightLeader = new CANSparkMax(CANIDConstants.krightDriveLeader, MotorType.kBrushless);
    private final CANSparkMax m_rightFollow = new CANSparkMax(CANIDConstants.krightDriveFollow, MotorType.kBrushless);
    private final CANSparkMax m_leftLeader = new CANSparkMax(CANIDConstants.kleftDriveLeader, MotorType.kBrushless);
    private final CANSparkMax m_leftFollow = new CANSparkMax(CANIDConstants.kleftDriveFollow, MotorType.kBrushless);

    public DriveSubsystem() {
        m_leftLeader.setInverted(true);
        m_leftFollow.setInverted(true);

        m_rightFollow.follow(m_rightLeader);
        m_leftFollow.follow(m_leftLeader);
    }
 
    public void arcadeDrive(double forward, double rotation) {
        if (Math.abs(forward) < ControllerConstants.kdeadZone) {
            forward = 0;
        }
        if (Math.abs(rotation) < ControllerConstants.kdeadZone){
            rotation = 0;
        }
        m_rightLeader.set((forward - rotation)/Constants.ControllerConstants.kDriveControl);
        m_leftLeader.set((forward + rotation)/Constants.ControllerConstants.kDriveControl);
    }
    
    public void stop() {
        m_rightLeader.set(0);
        m_leftLeader.set(0);
    }

}