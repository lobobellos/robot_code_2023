package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoulder;

public class MoveShoulderTo extends CommandBase{

  Shoulder shoulder;

  public MoveShoulderTo(Shoulder shoulder,double setpoint){

    shoulder.setSetpoint(setpoint);
    addRequirements(shoulder);
    shoulder.getController().enableContinuousInput(-180, 180);
    
    this.shoulder = shoulder;
  }

  @Override
  public boolean isFinished(){
    return shoulder.atSetpoint();
  }
}
