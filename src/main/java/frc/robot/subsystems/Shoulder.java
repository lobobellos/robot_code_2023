package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Const;

public class Shoulder extends PIDSubsystem{

  //shoulder motors
  CANSparkMax shoulderL = new CANSparkMax(Const.Shoulder.ID_L, MotorType.kBrushless);
  CANSparkMax shoulderR = new CANSparkMax(Const.Shoulder.ID_R, MotorType.kBrushless);
  MotorControllerGroup shoulder;

  Encoder encoder = new Encoder(
    Const.Shoulder.encoderA,
    Const.Shoulder.encoderB,
    Const.Shoulder.encoderReversed
  );

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0);
  
  public Shoulder(){
    super(
      new PIDController(Const.Shoulder.kp, Const.Shoulder.ki, Const.Shoulder.kd)
    );

    shoulderR.setInverted(true);
    shoulder = new MotorControllerGroup(shoulderL, shoulderR);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    shoulder.setVoltage(output + feedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return encoder.getRate();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }  
}
