package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Claw extends SubsystemBase{
  WPI_VictorSPX clawMotor = new WPI_VictorSPX(Const.claw.ID);

  Encoder encoder = new Encoder(0, 1);

  public Claw(){
    encoder.reset();
    setDefaultCommand(
      new RunCommand(
        ()->clawMotor.stopMotor(),
        this
      )
    );
  }

  public void periodic(){
    SmartDashboard.putNumber("encoder position", encoder.getDistance());
  }

  public void set(double speed){
    clawMotor.set(speed);
  }

  public void stop(){
    clawMotor.stopMotor();
  }

}
