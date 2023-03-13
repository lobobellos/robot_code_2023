package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Shoulder extends SubsystemBase{
  
  CANSparkMax

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
    for(RelativeEncoder e : encoders){
      e.setPosition(0);
    }
  }

  public void set(double speed){
    shoulder.set(speed);
  }

  public void periodic(){

    double[] encoderPos = new double[4];

    for(int i=0;i<4;i++){
      encoderPos[i] = encoders[i].getPosition();
    }

    SmartDashboard.putNumberArray("encoders", encoderPos);
  }
}
