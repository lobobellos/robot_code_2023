package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
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

  double setpoint = 0;

  boolean enabled = true;

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

    setDefaultCommand(new RunCommand(
        ()->{
          controller.setReference(getSetPoint(), ControlType.kPosition);
        },
        this
      )
    );
  }

  public Runnable runPID = ()->controller.setReference(getSetPoint(), ControlType.kPosition);
  public Runnable release = ()->controller.setReference(0, ControlType.kVoltage);

  public void setSetPoint(double setpoint){
    this.setpoint = setpoint;
  }

  public double getSetPoint(){
    return setpoint;
  }

  public void periodic(){ 
    SmartDashboard.putNumber("shoulder position", encoder.getPosition());
    SmartDashboard.putNumber("shoulder setpoint", getSetPoint());
    SmartDashboard.putNumber("shoulder applied output",shoulder.getAppliedOutput());
  }

  public RelativeEncoder getEncoder() {
    return encoder;
  }

  public InstantCommand moveUp(){
    return new InstantCommand(
      ()->setpoint--
    );
  }
  
  public InstantCommand moveDown(){
    return new InstantCommand(
      ()->setpoint++
    );
  }

  public InstantCommand moveTo(double setpoint){
    return new InstantCommand(
      ()->this.setpoint = setpoint
    );
  }

  public InstantCommand setEnabled(boolean enabled){
    return new InstantCommand(
      ()->this.enabled = enabled
    );
  }
}
