package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Const;
import frc.robot.subsystems.Claw;

public class MoveHand extends RunCommand {
  public MoveHand(Claw claw,boolean reverse){
    super(
      ()->claw.set(reverse ? Const.claw.speed : -Const.claw.speed ),
      claw
    );
  }
}
