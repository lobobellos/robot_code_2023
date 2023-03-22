package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;  
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ResetEncoders;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class UnloadArm extends SequentialCommandGroup {

  static boolean runShoulder = true;
  static boolean runElbow = true;


  public UnloadArm(Shoulder shoulder,Elbow elbow){
    super(
      new ParallelCommandGroup(
        new RunCommand(
        ()->{
          if(runShoulder){
            shoulder.runPID.run();
          }else{
            shoulder.release.run();
          }
          if(runElbow){
            elbow.runPID.run();
          }else{
            elbow.release.run();
          }
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

        elbow.moveTo(-6),
        new WaitCommand(0.5),

        shoulder.moveTo(5.5),
        new WaitCommand(0.5),

        elbow.moveTo(-15),
        new WaitCommand(0.75),


        shoulder.moveTo(6.5),
        new WaitCommand(0.75),
        new WaitCommand(1),
        shoulder.moveTo(12),

        new InstantCommand(()->runShoulder = false),
        new WaitCommand(2),

        elbow.moveTo(-23)
        )
      )
       
    );
  }
}
