package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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

  CANSparkMax[] controllers = {
    new CANSparkMax(Const.shoulder.ids.fl, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.rl, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.fr, MotorType.kBrushless),
    new CANSparkMax(Const.shoulder.ids.rr, MotorType.kBrushless)
  };

  //encoders
  RelativeEncoder[] encoders = {
    controllers[0].getEncoder(),
    controllers[1].getEncoder(),
    controllers[2].getEncoder(),
    controllers[3].getEncoder()
  };

  //motor group
  MotorControllerGroup shoulder = new MotorControllerGroup(controllers);

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
    for(RelativeEncoder e : encoders){
      e.setPosition(0);
    }

    shoulder.setInverted(true);
  }

  public void set(double speed){
    shoulder.set(speed);
  }

  public void periodic(){
    for(int i=0;i<4;i++){
      SmartDashboard.putNumber("encoder "+i, encoders[i].getPosition());
    }
  }
}
