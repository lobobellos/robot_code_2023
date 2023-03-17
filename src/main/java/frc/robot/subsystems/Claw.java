package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Claw extends SubsystemBase{
  WPI_VictorSPX clawMotor = new WPI_VictorSPX(Const.claw.ID);

  Encoder encoder = new Encoder(0, 1,true);

  PIDController controller = new PIDController(
    Const.claw.pidff.kP,
    Const.claw.pidff.kI,
    Const.claw.pidff.kD
  );
    
  SimpleMotorFeedforward ff = new SimpleMotorFeedforward(
    Const.claw.pidff.kS,  
    Const.claw.pidff.kV  
  );

  double setpoint = 0;

  public Claw(){
    encoder.reset();
    setDefaultCommand(new RunCommand(
      ()->{
        runPID(setpoint);
      },
      this
    ));

    addChild("clawMotor",clawMotor);
  }

  public void periodic(){
    SmartDashboard.putNumber("claw encoder", encoder.getDistance());
  }

  public void runPID(double setpoint){
    clawMotor.set(
      controller.calculate(
        encoder.getDistance()
      )
    );
  }

  public void setSetPoint(double setpoint){
    this.setpoint =  setpoint;
  }

  public void stop(){
    clawMotor.stopMotor();
  }

  public Encoder getEncoder(){
    return encoder;
  }

}
