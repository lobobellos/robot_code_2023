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
    clawMotor.setInverted(true);
    encoder.reset();
    setDefaultCommand(new RunCommand(
      ()->runPID(),
      this
    ));

    addChild("clawMotor",clawMotor);
  }

  public void periodic(){
    SmartDashboard.putNumber("claw encoder", encoder.getDistance());
    SmartDashboard.putNumber("claw setpoint", setpoint);

    SmartDashboard.putNumberArray("claw position",new double[]{encoder.getDistance()});
  }

  public void runPID(){
    clawMotor.set(
      controller.calculate(
        encoder.getDistance(),
        setpoint
      )
    );
  }

  public void setSetPoint(double setpoint){
    this.setpoint =  setpoint;
  }



  public Encoder getEncoder(){
    return encoder;
  }

}
