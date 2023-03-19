package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class ReleaseAndZero extends SequentialCommandGroup{

  public ReleaseAndZero(Shoulder shoulder,Elbow elbow){
    super(
      new InstantCommand(
        ()->{
          shoulder.setEnabled(false);
          elbow.setEnabled(false);
        }
      ),
      new WaitCommand(1.5),
      new ResetEncoders(shoulder,elbow),
      new InstantCommand(
        ()->{
          shoulder.setEnabled(true);
          elbow.setEnabled(true);
        }
      )
      
    );
  }
}
