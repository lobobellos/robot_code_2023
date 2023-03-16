package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Shoulder extends SubsystemBase{

  CANSparkMax shoulder = new CANSparkMax(Const.shoulder.ids.fl, MotorType.kBrushless);

  CANSparkMax[] controllers = {
    new CANSparkMax(Const.shoulder.ids.rl, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.fr, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.rr, MotorType.kBrushless)
  };

  //encoder
  RelativeEncoder encoder = shoulder.getEncoder();
  SparkMaxPIDController controller = shoulder.getPIDController();

  public Shoulder(){

    encoder.setPosition(0);
    for(CANSparkMax s : controllers){s.follow(shoulder);}
    shoulder.setInverted(true);

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
    shoulder.getPIDController().setReference(setpoint, ControlType.kPosition);
  }

  public void periodic(){
    SmartDashboard.putNumber("shoulder encoder ", encoder.getPosition());
  }

  public RelativeEncoder getEncoder() {
    return encoder;
  }
}
