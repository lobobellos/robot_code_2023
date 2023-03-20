package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ReleaseAndZero;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous(Shoulder shoulder,Elbow elbow){
    super(
      new InstantCommand(
        // TODO: set the values
        ()->shoulder.setSetPoint(0)
      ),
      new WaitCommand(2),
      new InstantCommand(
        // TODO: set the values
        ()->elbow.setSetPoint(0)
      ),
      new WaitCommand(2),
      new ReleaseAndZero(shoulder, elbow)
    );
  }
}
