package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase{
  
  ADIS16448_IMU gyro = new ADIS16448_IMU();

  public Gyro(){
    calibrate();
  }

  public void calibrate(){
    gyro.calibrate();
  }

  public double getAngle(){
    return gyro.getGyroAngleZ();
  }

  public void periodic(){
    SmartDashboard.putNumber("z angle", getAngle());
  }
}
