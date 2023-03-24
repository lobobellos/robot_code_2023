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
  static boolean runElbow = false;

  static boolean toEnd = false;

  public UnloadArm(Shoulder shoulder,Elbow elbow){
    super(
      new InstantCommand(
        ()->{
          runShoulder = true;
          runElbow = false;
          toEnd = false;
          System.out.println("inside the thing");
        }
      ),
      new ParallelCommandGroup(
        new RunUntilStop(
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
        ()->toEnd
      ),
      new SequentialCommandGroup(
        new ResetEncoders(shoulder, elbow),
        shoulder.moveTo(2.0),
        new WaitCommand(1),
        shoulder.moveTo(3),
        new WaitCommand(0.75),
        shoulder.moveTo(4),
        new WaitCommand(0.75),
        shoulder.moveTo(5.5),
        new WaitCommand(0.5),
        shoulder.moveTo(6.5),


        //safe code

        new InstantCommand(()->runShoulder = false),
        new ResetEncoders(shoulder, elbow),
        new InstantCommand(()->runElbow = true),
        
        new WaitCommand(0.5),

        elbow.moveTo(-10),
        new WaitCommand(1.5),

        elbow.moveTo(-20),
        new WaitCommand(1.5),
        elbow.moveTo(-40),
        new WaitCommand(1.5),
        

        elbow.moveTo(-60),
        new WaitCommand(1.5),
        
        new InstantCommand(()->runElbow = false)

        )
      )
       
    );
    System.out.println("construvtor");
  }
}
