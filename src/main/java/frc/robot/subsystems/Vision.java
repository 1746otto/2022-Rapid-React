package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
  private boolean validTarget;
  private double xOffset;
  private double yOffset;
  private double targetArea;
  private double skew;
  private double pipeLatency;
  private double tshort;
  private double tlong;
  private double thor;
  private double tvert;
  private double getpipe;
  private double camtran;

  public void fetchvision() {
    try {
      validTarget = (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv")
        .getDouble(0.0) == 1) ? true : false;
      xOffset = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
      yOffset = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
      targetArea = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
      skew = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0.0);
      pipeLatency = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0.0);
      tshort = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(0.0);
      tlong = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong").getDouble(0.0);
      thor = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0.0);
      tvert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0.0);
      getpipe = NetworkTableInstance.getDefault().getTable("limelight").getEntry("getpipe").getDouble(0.0);
      camtran = NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getDouble(0.0);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean isTargetValid() {
    return validTarget;
  }

  public double getXOffset() {
    return xOffset;
  }

  public double getYOffset() {
    return yOffset;
  }

  public double getTargetArea() {
    return targetArea;
  }

  public double getSkew() {
    return skew;
  }

  public double getPipeLatency() {
    return pipeLatency;
  }

  public double getTShort() {
    return tshort;
  }

  public double getTLong() {
    return tlong;
  }

  public double getThor() {
    return thor;
  }

  public double getTvert() {
    return tvert;
  }

  public double getGetPipe() {
    return getpipe;
  }

  public double getCamTran() {
    return camtran;
  }
}