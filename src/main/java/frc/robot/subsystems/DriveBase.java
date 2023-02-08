package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Const.drive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveBase extends SubsystemBase{

  private CANSparkMax fl = new CANSparkMax(drive.fl,MotorType.kBrushless );
  private CANSparkMax fr = new CANSparkMax(drive.fr,MotorType.kBrushless );
  private CANSparkMax rl = new CANSparkMax(drive.rl,MotorType.kBrushless );
  private CANSparkMax rr = new CANSparkMax(drive.rr,MotorType.kBrushless );

  private MecanumDrive driveBase;
  
  public DriveBase(){

    fr.setInverted(true);
    rr.setInverted(true);

    driveBase = new MecanumDrive(fl,rl,fr,rr);

    addChild("driveBase",driveBase);
  }

  public void drive(double x,double y, double z){
    driveBase.driveCartesian(x, y, z);
  }

  public void stop(){
    driveBase.driveCartesian(0,0,0);
  }

}
