package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class ResetEncoders extends InstantCommand{
  public ResetEncoders(Shoulder shoulder,Elbow elbow,Claw claw){
    super(
      ()->{
        shoulder.getEncoder().setPosition(0);
        elbow.getEncoder().setPosition(0);
        claw.getEncoder().reset();
      },
      shoulder,elbow,claw);
  }
}
