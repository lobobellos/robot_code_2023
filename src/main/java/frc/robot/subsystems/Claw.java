package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Claw extends SubsystemBase{
  CANSparkMax clawMotor = new CANSparkMax(Const.claw.ID,MotorType.kBrushed );

  RelativeEncoder encoder = clawMotor.getEncoder();

  public Claw(){
    encoder.setPosition(0);

  }

  public void periodic(){
    SmartDashboard.putNumber("encoder position", encoder.getPosition());
  }

  public void set(double speed){
    clawMotor.set(speed);
  }


}
