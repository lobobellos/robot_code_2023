package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Foot;

public class EngageFoot extends RunCommand{

  public EngageFoot(Foot foot){
    super(
      ()->foot.set(DoubleSolenoid.Value.kForward),
      foot
    );
  }
}
