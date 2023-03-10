package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Const;

public class Shoulder extends PIDSubsystem{

  //shoulder motors
  CANSparkMax shoulderL = new CANSparkMax(Const.Shoulder.ID_L, MotorType.kBrushless);
  CANSparkMax shoulderR = new CANSparkMax(Const.Shoulder.ID_R, MotorType.kBrushless);
  MotorControllerGroup shoulder;

  RelativeEncoder[] encoders = {
    shoulderL.getEncoder(),
    shoulderR.getEncoder()
  };

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Const.Shoulder.kG, Const.Shoulder.kV);
  
  public Shoulder(){
    super(
      new PIDController(Const.Shoulder.kP, Const.Shoulder.kI, Const.Shoulder.kD)
    );
    getController().setTolerance(Const.Shoulder.tolerance);

    shoulderR.setInverted(true);
    shoulder = new MotorControllerGroup(shoulderL, shoulderR);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    shoulder.setVoltage(output + feedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return encoders[0].getPosition();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }  

  
}
