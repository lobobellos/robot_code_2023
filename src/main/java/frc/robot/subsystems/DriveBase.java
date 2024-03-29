package frc.robot.subsystems;
import frc.robot.Const;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveBase extends SubsystemBase{

  private CANSparkMax fl = new CANSparkMax(Const.drive.fl,MotorType.kBrushless );
  private CANSparkMax fr = new CANSparkMax(Const.drive.fr,MotorType.kBrushless );
  private CANSparkMax rl = new CANSparkMax(Const.drive.rl,MotorType.kBrushless );
  private CANSparkMax rr = new CANSparkMax(Const.drive.rr,MotorType.kBrushless );

  private RelativeEncoder[] encoders = {
    fl.getEncoder(),
    fr.getEncoder(),
    rl.getEncoder(),
    rr.getEncoder()
  };

  private MecanumDrive driveBase;

  ADIS16448_IMU gyro = new ADIS16448_IMU();

  boolean withGyro = true;
  
  public DriveBase(){

    fr.setInverted(true);
    rr.setInverted(true);

    driveBase = new MecanumDrive(fl,rl,fr,rr);

    addChild("driveBase",driveBase);
    setName("drivebase");
  }



  public void drive(double x,double y, double z,boolean applyMichaelRule){
    
    x = MathUtil.applyDeadband(x, 0.05);
    y = MathUtil.applyDeadband(y, 0.05);
    z = MathUtil.applyDeadband(z, 0.05);

    
    
    if(withGyro){
      driveBase.driveCartesian(
         -x,
         y,
        z*Const.drive.rotateSensitivity,
        getAngleZ()
      );
    }else{
      driveBase.driveCartesian(
        -x,
         y,
        z*Const.drive.rotateSensitivity
      );

    }
  }

  public InstantCommand setWithGyro(boolean withGyro){
    return new InstantCommand(
      ()->this.withGyro = withGyro
    );
  }

  public void stop(){
    driveBase.driveCartesian(0,0,0);
  }

  public Runnable calibrate(){
    return ()->gyro.calibrate();
  }

  public RelativeEncoder[] getEncoders(){
    return encoders;
  }

  public double getAverageDist(){
    double sum = 0;
    for(RelativeEncoder e : encoders){
      sum += e.getPosition();
    }
    return sum / encoders.length;
  }

  public void resetEncoders(){
    for(RelativeEncoder e : encoders){
      e.setPosition(0);
    }
  }

  public Rotation2d getAngleZ(){
    return Rotation2d.fromDegrees(gyro.getGyroAngleZ());
  }
  public Rotation2d getAngleY(){
    return Rotation2d.fromDegrees(gyro.getGyroAngleY());
  }
  public Rotation2d getAngleX(){
    return Rotation2d.fromDegrees(gyro.getGyroAngleX());
  }

  public void periodic(){
    SmartDashboard.putNumber("z angle", getAngleZ().getDegrees());
    SmartDashboard.putNumber("db average dist", getAverageDist());
  }

}
