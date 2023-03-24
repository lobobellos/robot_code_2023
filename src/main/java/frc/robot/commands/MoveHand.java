package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Const;
import frc.robot.subsystems.Claw;

public class MoveHand extends InstantCommand {

  public enum position{
    open,
    closed,
  }

  public MoveHand(Claw claw,position pos){
    super(
      ()->claw.setSetPoint(
        pos == position.open?
        Const.claw.startPoint:
        Const.claw.endPoint
      )
    );
  }
}
