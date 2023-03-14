package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Claw extends SubsystemBase{
  VictorSPX clawMotor = new VictorSPX(Const.claw.ID);

  Encoder encoder = new Encoder(0, 1);

  public Claw(){
    encoder.reset();

  }

  public void periodic(){
    SmartDashboard.putNumber("encoder position", encoder.getDistance());
  }

  public void set(double speed){
    clawMotor.set(VictorSPXControlMode.PercentOutput,speed);
  }

}
