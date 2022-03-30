package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PicoColorSensor;
import frc.robot.Constants.ColorSensorConstants;


public class ColorSensorSubsystem extends SubsystemBase {

  PicoColorSensor colorSensor = null;

  /** Creates a new ExampleSubsystem. */
  public ColorSensorSubsystem() {
    colorSensor = new PicoColorSensor();
  }

  public boolean isBallRed() {
    PicoColorSensor.RawColor rawColor = colorSensor.getRawColor0();
    if (rawColor.red >= ColorSensorConstants.kRawHigh
        && rawColor.blue <= ColorSensorConstants.kRawLowValue
        && rawColor.green <= ColorSensorConstants.kRawLowValue) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isBallBlue() {
    PicoColorSensor.RawColor rawColor = colorSensor.getRawColor0();
    if (rawColor.blue >= ColorSensorConstants.kRawHigh
        && rawColor.red <= ColorSensorConstants.kRawLowValue
        && rawColor.green <= ColorSensorConstants.kRawLowValue) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }



}
