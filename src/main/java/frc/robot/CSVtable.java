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
    
        public void table() {
            try
            {
                FileWriter newWriter = new FileWriter(table, true);
                newWriter.write((int)m_shooterSubsystem.RPM() + "/n");
                newWriter.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

    }
}

    

        
    
    

