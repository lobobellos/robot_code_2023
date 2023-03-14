package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  
  private VictorSPX motor = new VictorSPX(Const.Elbow.motorID);
  private Servo servo = new Servo(Const.Elbow.servoID);
  private DutyCycleEncoder encoder = new DutyCycleEncoder(Const.Elbow.encoderDIOpin);
  
  public Elbow(){
    // TODO: initialization?
  }

  //create method to set the motorcontroller
  // TODO: establish desired control mode
  public void setMotor(double speed) {
    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  //create method to actuate the servo
  public void setServo(double position) {
    servo.set(position);
  }

  public void periodic(){
    //put values on smartDashboard
    SmartDashboard.putNumber("Elbow motor speed ", motor.get());
    SmartDashboard.putNumber("Elbow servo ", servo.getPosition());
    SmartDashboard.putNumber("Elbow encoder ", encoder.get());
  }

}
