package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Const;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveBase extends SubsystemBase{

  private CANSparkMax fl = new CANSparkMax(Const.drive.fl,MotorType.kBrushless );
  private CANSparkMax fr = new CANSparkMax(Const.drive.fr,MotorType.kBrushless );
  private CANSparkMax rl = new CANSparkMax(Const.drive.rl,MotorType.kBrushless );
  private CANSparkMax rr = new CANSparkMax(Const.drive.rr,MotorType.kBrushless );

  private MecanumDrive driveBase;
  
  public DriveBase(){

    fr.setInverted(true);
    rr.setInverted(true);

    driveBase = new MecanumDrive(fl,rl,fr,rr);

    addChild("driveBase",driveBase);
  }

  public void drive(double x,double y, double z){
    driveBase.driveCartesian(-x, y, z*Const.drive.rotateSensitivity);
  }

  public void stop(){
    driveBase.driveCartesian(0,0,0);
  }

}
