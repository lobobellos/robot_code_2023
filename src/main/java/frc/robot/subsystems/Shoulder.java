package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Shoulder extends SubsystemBase{
  
  /*
   * 0 - fl
   * 1 - rl
   * 2 - fr
   * 3 - rr
   */
  CANSparkMax shoulder = new CANSparkMax(Const.shoulder.ids.fl, MotorType.kBrushless);

  CANSparkMax[] controllers = {
    new CANSparkMax(Const.shoulder.ids.rl, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.fr, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.rr, MotorType.kBrushless)
  };

  //encoders
  RelativeEncoder encoder = shoulder.getEncoder();

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
    Const.shoulder.kG,
    Const.shoulder.kV
  );
  
  PIDController controller = new PIDController(
    Const.shoulder.kP,
    Const.shoulder.kI, 
    Const.shoulder.kD
  );

  public Shoulder(){

    encoder.setPosition(0);

    for(CANSparkMax s : controllers){
      s.follow(shoulder);
    }

    shoulder.setInverted(true);
  }

  public void set(double speed){
    shoulder.set(speed);
  }

  public void periodic(){
    SmartDashboard.putNumber("shoulder encoder ", encoder.getPosition());
  }

  public RelativeEncoder getEncoder() {
    return encoder;
  }
}
