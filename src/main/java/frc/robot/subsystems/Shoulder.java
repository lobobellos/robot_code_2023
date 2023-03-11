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
  CANSparkMax[] sparkMaxes = {
    new CANSparkMax(Const.shoulder.ids[0], MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids[1], MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids[2], MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids[3], MotorType.kBrushless)
  };

  //encoders
  RelativeEncoder[] encoders = {
    sparkMaxes[0].getEncoder(),
    sparkMaxes[1].getEncoder(),
    sparkMaxes[2].getEncoder(),
    sparkMaxes[3].getEncoder()
  };

  //motor group
  MotorControllerGroup shoulder = new MotorControllerGroup(sparkMaxes);

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Const.shoulder.kG, Const.shoulder.kV);
  
  public Shoulder(){
    super(
      new PIDController(Const.shoulder.kP, Const.shoulder.kI, Const.shoulder.kD)
    );
    getController().setTolerance(Const.shoulder.tolerance);


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

  public void set(double speed){
    shoulder.set(speed);
  }
  
}
