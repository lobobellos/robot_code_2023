package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ResetEncoders;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class UnloadArm extends ParallelCommandGroup {
  public UnloadArm(Shoulder shoulder,Elbow elbow){
    super(
      new RunCommand(
        ()->{
          shoulder.runPID.run();
          elbow.runPID.run();
        },
        shoulder,elbow
      ),
      new SequentialCommandGroup(
        new ResetEncoders(shoulder, elbow),
        shoulder.moveTo(2.0),
        new WaitCommand(2),
        shoulder.moveTo(3),
        new WaitCommand(0.75),
        shoulder.moveTo(4),
        new WaitCommand(0.75),
        shoulder.moveTo(5.5),
        new WaitCommand(0.75),
        shoulder.moveTo(6.5),
        new WaitCommand(0.75),
        //shoulder is horisontal now
        new ResetEncoders(shoulder, elbow),
        elbow.moveTo(-2),
        
        new WaitCommand(3),
        shoulder.moveTo(2)
        )
    );
  }
}
