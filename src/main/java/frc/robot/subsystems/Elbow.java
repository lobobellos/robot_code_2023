package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  //sparkmax stuff
  CANSparkMax motor = new CANSparkMax(Const.elbow.motorID, MotorType.kBrushless);
  RelativeEncoder encoder = motor.getEncoder();
  SparkPIDController controller = motor.getPIDController();
  //for pid control
  double setpoint = 0;
  
  public Elbow(){
    //reset
    encoder.setPosition(0);
    // set PID coefficients
    controller.setP(Const.elbow.pidff.kP);
    controller.setI(Const.elbow.pidff.kI);
    controller.setD(Const.elbow.pidff.kD);
    controller.setIZone(Const.elbow.pidff.kIz);
    controller.setFF(Const.elbow.pidff.kFF);
    controller.setOutputRange(
      Const.elbow.pidff.minOutput,
      Const.elbow.pidff.maxOutput
    );

    motor.setIdleMode(IdleMode.kCoast);
    setName("Elbow");
  }

  public Runnable runPID = ()->controller.setReference(getSetPoint(), ControlType.kPosition,0);

  public Runnable release = ()->controller.setReference(0, ControlType.kVoltage);

  public void setSetPoint(double setpoint){
    this.setpoint = setpoint; 
  }
  
  public double getSetPoint(){
    return setpoint;
  }

  public RelativeEncoder getEncoder(){
    return encoder;
  }
  
  public void periodic(){
    //put values on smartDashboard
    SmartDashboard.putNumber("Elbow encoder ", encoder.getPosition());
    SmartDashboard.putNumber("Elbow setpoint ", getSetPoint());
    SmartDashboard.putNumber("elbow applied output",motor.getAppliedOutput());

    SmartDashboard.putNumberArray("elbow position",new double[]{encoder.getPosition()});
  }

  public InstantCommand moveUp(){
    return new InstantCommand(
      ()->setpoint+=4
    );
  }
  
  public InstantCommand moveDown(){
    return new InstantCommand(
      ()->setpoint-=4
    );
  }

  public InstantCommand moveTo(double setpoint){
    return new InstantCommand(
      ()->this.setpoint = setpoint
    );
  }
  public Runnable move(double toAdd){
    return ()->this.setpoint+= toAdd;
  }

}
