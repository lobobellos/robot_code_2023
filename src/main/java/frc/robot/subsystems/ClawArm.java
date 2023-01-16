package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class ClawArm extends SubsystemBase {
  
  //shoulder motors
  CANSparkMax shoulderL = new CANSparkMax(Const.shoulderL, MotorType.kBrushless);
  CANSparkMax shoulderR = new CANSparkMax(Const.shoulderL, MotorType.kBrushless);
  MotorControllerGroup shoulder;

  //elbow motor
  CANSparkMax elbow = new CANSparkMax(Const.elbow, MotorType.kBrushless);
  
  //wrist motors
  CANSparkMax wristRoll = new CANSparkMax(Const.wristRoll, MotorType.kBrushless);
  CANSparkMax wristPitch = new CANSparkMax(Const.wristPitch, MotorType.kBrushless);

  //claw motor
  CANSparkMax claw = new CANSparkMax(Const.claw, MotorType.kBrushless);
  
  public ClawArm(){
    //create shoulder class
    shoulderL.setInverted(true);
    shoulder = new MotorControllerGroup(shoulderL,shoulderR);
  }


  public void stop(){
    shoulder.stopMotor(); 
    elbow.stopMotor(); 
    wristRoll.stopMotor(); 
    wristPitch.stopMotor(); 
    claw.stopMotor(); 
  }
  

  @Override
  public void periodic() {
    stop();
  }

}
