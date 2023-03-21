package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class Release extends WaitCommand{

  Shoulder shoulder;
  Elbow elbow;

  public Release(Elbow elbow,Shoulder shoulder, double time){
    super(time);
    this.shoulder = shoulder;
    this.elbow = elbow;

    addRequirements(elbow,shoulder);
  }

  public void execute(){
    shoulder.release.run();
    elbow.release.run();
  }
}
