package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;  
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ResetEncoders;
import frc.robot.commands.MoveHand;
import frc.robot.commands.ReleaseAndZero;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Shoulder;

public class ScoreHigh extends SequentialCommandGroup {

  static boolean runShoulder;
  static boolean runElbow;

  static boolean toEnd = false;

  public ScoreHigh(Shoulder shoulder,Elbow elbow,Claw claw,DriveBase db){
    super(
      new InstantCommand(
        ()->{
          runShoulder = true;
          runElbow = true;
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
          //db.drive(0, 0, 0, false);
        }
        ),
        new SequentialCommandGroup(
          //shoulder up
          new ResetEncoders(shoulder, elbow),

          new InstantCommand(()->runElbow = false),

          shoulder.moveTo(1.0),
          new WaitCommand(1.5),

          //grab with claw
          new MoveHand(claw, MoveHand.position.closed),
          new WaitCommand(1.5),
          
          shoulder.moveTo(2.0),
          new WaitCommand(0.5),

          shoulder.moveTo(3.0),
          new WaitCommand(0.5),

          //shoulder up
          shoulder.moveTo(4.0),
          new WaitCommand(0.5),
          
          //shoulder up
          shoulder.moveTo(5.0),
          new WaitCommand(0.5),
          shoulder.moveTo(6.0),
          new WaitCommand(0.5),
          shoulder.moveTo(7.0),
          new WaitCommand(0.5),

          new InstantCommand(()->runElbow = true),
          //shoulder and elbow more up

          elbow.moveTo(-30),
          new WaitCommand(1.0),

          elbow.moveTo(-40),
          new WaitCommand(1.0),

          new WaitCommand(1.0),
          elbow.moveTo(-50),

          elbow.moveTo(-60),
          new WaitCommand(1.0),

          shoulder.moveTo(6.0),
          new WaitCommand(0.5),

          elbow.moveTo(-70),
          shoulder.moveTo(5.5),
          new WaitCommand(1.0),

          
          //release game piece
          new MoveHand(claw, MoveHand.position.open),
          new WaitCommand(1.5),

          

          //elbow back
          elbow.moveTo(-46),
          new WaitCommand(0.5),
          elbow.moveTo(-32),
          new WaitCommand(0.5),

          // shoulder back
          shoulder.moveTo(4.0),
          new WaitCommand(0.5),

          

          //elbow back to neutral
          elbow.moveTo(0),
          shoulder.moveTo(1),
          new WaitCommand(0.5),

          new InstantCommand(
            ()->{
              runShoulder = false;
              runElbow = false;
            }
          ),

          new WaitCommand(1)
        )
      ),
      new ReleaseAndZero(shoulder, elbow)
    );
  }
}
