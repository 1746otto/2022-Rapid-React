package frc.robot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import java.io.File;
import java.io.FileWriter;



public class CSVtable {
    ShooterSubsystem m_shooterSubsystem; 
    File table;

    public CSVtable(){
        m_shooterSubsystem = new ShooterSubsystem();
        table = new File("table.CSV");
        
    }

    
       m_shooterSubsystem.RPM();
        
    
    
}
