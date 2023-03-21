package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class ReleaseAndZero extends SequentialCommandGroup{

  public ReleaseAndZero(Shoulder shoulder,Elbow elbow){
    super(
      new Release(elbow, shoulder, 2),
      new ResetEncoders(shoulder, elbow)
    );

    addRequirements(shoulder,elbow);
  }
}
