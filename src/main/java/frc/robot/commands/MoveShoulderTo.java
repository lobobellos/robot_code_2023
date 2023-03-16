package frc.robot.commands;

import com.revrobotics.SparkMaxPIDController;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoulder;

public class MoveShoulderTo extends CommandBase{

  Shoulder shoulder;

  DoubleSupplier setpoint;

  SparkMaxPIDController controller;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  //horisontal is -2.8

  // PID coefficients
 
  public MoveShoulderTo(Shoulder shoulder,DoubleSupplier setpoint){

    this.setpoint = setpoint;
    this.shoulder = shoulder;
    this.controller  = shoulder.getPIDController();

    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    controller.setP(kP);
    controller.setI(kI);
    controller.setD(kD);
    controller.setIZone(kIz);
    controller.setFF(kFF);
    controller.setOutputRange(kMinOutput, kMaxOutput);
    addRequirements(shoulder);
  }
  
  public void execute(){

    controller.setReference(setpoint.getAsDouble(), CANSparkMax.ControlType.kPosition);
    
    SmartDashboard.putNumber("SetPoint", setpoint.getAsDouble());
    SmartDashboard.putNumber("ProcessVariable", shoulder.getEncoder().getPosition());
  }

  public boolean isFinished(){
    return false;
  }

}
