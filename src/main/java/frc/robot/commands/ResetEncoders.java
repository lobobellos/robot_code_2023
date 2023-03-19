package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class ResetEncoders extends InstantCommand{
  public ResetEncoders(Shoulder shoulder,Elbow elbow){
    super(
      ()->{
        //reset encoders
        shoulder.getEncoder().setPosition(0);
        elbow.getEncoder().setPosition(0);
        //reset setpoints
        shoulder.setSetPoint(0);
        elbow.setSetPoint(0);
      }
    );
  }
}
