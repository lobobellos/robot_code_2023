package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  
  private WPI_VictorSPX motor = new WPI_VictorSPX(Const.elbow.motorID);
  private Servo servo = new Servo(Const.elbow.servoID);

  private Encoder encoder = new Encoder(
    Const.elbow.encoder.pinA,
    Const.elbow.encoder.pinB
  );
  
  public Elbow(){
    // TODO: initialization?
  }

  //create method to set the motorcontroller
  // TODO: establish desired control mode
  public void setMotor(double speed) {
    motor.set(speed);
  }

  //create method to actuate the servo
  public void setServo(double position) {
    servo.set(position);
  }

  public void periodic(){
    //put values on smartDashboard
    SmartDashboard.putNumber("Elbow servo ", servo.getPosition());
    SmartDashboard.putNumber("Elbow encoder ", encoder.get());
  }

  public Encoder getEncoder(){
    return encoder;
  }

}
