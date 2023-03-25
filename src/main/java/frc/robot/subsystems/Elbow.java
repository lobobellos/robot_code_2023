package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends SubsystemBase{
  //sparkmax stuff
  CANSparkMax NEOmotor = new CANSparkMax(Const.elbow.motorID, MotorType.kBrushless);
  RelativeEncoder encoder = NEOmotor.getEncoder();
  SparkMaxPIDController controller = NEOmotor.getPIDController();
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

    NEOmotor.setIdleMode(IdleMode.kCoast);

    setDefaultCommand(new RunCommand(
        runPID,
        this
      )
    );
  }

  public Runnable runPID = ()->controller.setReference(getSetPoint(), ControlType.kPosition);

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
    SmartDashboard.putNumber("elbow applied output",NEOmotor.getAppliedOutput());

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
  public InstantCommand move(double toAdd){
    return new InstantCommand(
      ()->this.setpoint+= toAdd
    );
  }



}
