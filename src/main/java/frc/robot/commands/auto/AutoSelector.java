package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.DriveForwards;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Foot;
import frc.robot.subsystems.Shoulder;

public class AutoSelector extends CommandBase {
  
  public static enum AutoType{
    left,
    right,
    center
  }

  final SendableChooser<AutoType> autoChooser= new SendableChooser<AutoType>();

  final CommandScheduler scheduler=CommandScheduler.getInstance();

  final DriveBase db;
  final Shoulder shoulder;
  final Elbow elbow;
  final Foot foot;

  public AutoSelector(DriveBase db,Shoulder shoulder,Elbow elbow,Foot foot){

    this.db = db;
    this.shoulder = shoulder;
    this.elbow = elbow;
    this.foot = foot;

    autoChooser.setDefaultOption("left", AutoType.left);
    autoChooser.addOption("right", AutoType.right);
    autoChooser.addOption("center", AutoType.center);
    autoChooser.addOption("left", AutoType.left);
    SmartDashboard.putData("Auto choices", autoChooser);

  }
  
  @Override
  public void initialize() {

    switch(  autoChooser.getSelected().name()){
      case "left":
      scheduler.schedule(
        new SequentialCommandGroup(

          new UnloadArm(shoulder, elbow)
        //new DriveForwards(db,25)
        )
        );

      break;
      case "center":
      scheduler.schedule(
        new SequentialCommandGroup(
          new DriveForwards(db, 50),
          foot.setSolenoid(DoubleSolenoid.Value.kReverse),
          new WaitCommand(2)
        )
      );
      break;
      case "right":
      scheduler.schedule(
        new SequentialCommandGroup(
          //new UnloadArm
          new DriveForwards(db, 40)
        )
      ); //TODO: make third auto
      break;
    } 
    
  }
}
