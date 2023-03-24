package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;  
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ResetEncoders;
import frc.robot.commands.MoveHand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class UnloadArm extends SequentialCommandGroup {

  static boolean runShoulder = true;
  static boolean runElbow = false;

  static boolean toEnd = false;

  public UnloadArm(Shoulder shoulder,Elbow elbow,Claw claw,DriveBase db){
    super(
      new InstantCommand(
        ()->{
          runShoulder = true;
          runElbow = false;
          toEnd = false;
        }
      ),
      new ParallelRaceGroup(
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

          claw.runPID();
          db.drive(0, 0, 0, false);
        }
        ),
        new SequentialCommandGroup(
          //shoulder up
          new ResetEncoders(shoulder, elbow),
          shoulder.moveTo(1.0),
          new WaitCommand(1),

          //grab with claw
          new MoveHand(claw, MoveHand.position.closed),
          new WaitCommand(1),
          
          //swing elbow out
          elbow.moveTo(-36),
          new WaitCommand(1.5),
          
          //release game piece
          new MoveHand(claw, MoveHand.position.open),
          new WaitCommand(1.5),
          
          //elbow Back in
          elbow.moveTo(0),
          new WaitCommand(1.5),
          
          //shoulder up
          shoulder.moveTo(4.0),
          new WaitCommand(0.5),
          
          //elbow out
          elbow.moveTo(-32),
          new WaitCommand(0.5),
          
          //shoulder  and elbow more up
          elbow.moveTo(-46),
          shoulder.moveTo(6.0),
          new WaitCommand(0.5),

          //disengage shoulder
          new InstantCommand(()->runShoulder = false),

          // flick elbow all the way down
          elbow.moveTo(-60),
          new WaitCommand(1),
          elbow.moveTo(-90),
          new WaitCommand(1),

          //calm down. everythings fine

          //reset encoders
          new ResetEncoders(shoulder, elbow)
        )
      )
    );
  }
}
