package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  
  CANSparkMax motor = new CANSparkMax(Const.elbow.motorID, MotorType.kBrushless);

  Servo servo = new Servo(Const.elbow.servoID);

  SparkMaxPIDController controller = motor.getPIDController();

  RelativeEncoder encoder = motor.getEncoder();
  
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
    SmartDashboard.putNumber("Elbow encoder ", encoder.getPosition());
  }

  public RelativeEncoder getEncoder(){
    return encoder;
  }

}
