package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  //servo
  // Servo servo = new Servo(Const.elbow.servoID);
  //sparkmax stuff
  CANSparkMax motor = new CANSparkMax(Const.elbow.motorID, MotorType.kBrushless);
  RelativeEncoder encoder = motor.getEncoder();
  SparkMaxPIDController controller = motor.getPIDController();
  //for pid control
  double setpoint = 0;
  
  public Elbow(){
    //reset
    encoder.setPosition(0);
    // set PID coefficients
    controller.setP(Const.shoulder.pidff.kP);
    controller.setI(Const.shoulder.pidff.kI);
    controller.setD(Const.shoulder.pidff.kD);
    controller.setIZone(Const.shoulder.pidff.kIz);
    controller.setFF(Const.shoulder.pidff.kFF);
    controller.setOutputRange(
      Const.shoulder.pidff.minOutput,
      Const.shoulder.pidff.maxOutput
    );
  }

  public void setSetPoint(double setpoint){
    this.setpoint = setpoint;
  }
  
  public double getSetPoint(){
    return setpoint;
  }

  //method to actuate the servo
  // public void setServo(double position) {
  //   servo.set(position);
  // }

  public RelativeEncoder getEncoder(){
    return encoder;
  }
  
  public void periodic(){
    controller.setReference(setpoint, ControlType.kPosition);
    //put values on smartDashboard
    //SmartDashboard.putNumber("Elbow servo ", servo.getPosition());
    SmartDashboard.putNumber("Elbow encoder ", encoder.getPosition());
    SmartDashboard.putNumber("Elbow setpoint ", setpoint);
  }

  public InstantCommand moveUp(){
    return new InstantCommand(
      ()->setpoint++
    );
  }
  
  public InstantCommand moveDown(){
    return new InstantCommand(
      ()->setpoint--
    );
  }

}
