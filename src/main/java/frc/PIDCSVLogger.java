package frc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class PIDCSVLogger {
    TalonSRX motor;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:MM:ss.SSS");
    private static DateTimeFormatter nanoDtf = DateTimeFormatter.ofPattern("MM:ss.nnnnnnnnn");
    private static String delimiter = ",";
    private BufferedWriter buffWritter;

    public PIDCSVLogger() {
        try {
            buffWritter = new BufferedWriter(new FileWriter("PidData.csv"));
        } catch (Exception e) {
            System.out.println("csv file open error " + e.getMessage());
        }


    }

    public void logPIDData(String message, TalonSRX motor) {
        double setValue = motor.getClosedLoopTarget();
        double error = motor.getClosedLoopError();
        boolean voltageCompEnabled = motor.isVoltageCompensationEnabled();
        double busVoltage = motor.getBusVoltage();
        double motorOutputPercent = motor.getMotorOutputPercent();
        double motorOutputVoltage = motor.getMotorOutputVoltage();
        double integralAccum = motor.getIntegralAccumulator();
        double derivitiveError = motor.getErrorDerivative();


        StringBuilder sb = new StringBuilder();
        LocalDate now = LocalDate.now();
        sb.append(now.format(dtf)).append(delimiter).append(now.format(nanoDtf)).append(delimiter)
                .append(setValue).append(delimiter).append(error).append(delimiter)
                .append(delimiter).append(busVoltage).append(delimiter).append(voltageCompEnabled)
                .append(delimiter).append(motorOutputPercent).append(delimiter)
                .append(motorOutputVoltage).append(delimiter).append(integralAccum)
                .append(delimiter).append(derivitiveError).append(delimiter).append(message);

        try {
            buffWritter.write(sb.toString());
            buffWritter.newLine();
        } catch (Exception e) {
            System.out.println("csv write error " + e.getMessage());
        }
    }

    public void closePidData() {
        try {
            buffWritter.flush();
            buffWritter.close();
        } catch (Exception e) {
            System.out.println("Flush and close error " + e.getMessage());
        }
    }
}
