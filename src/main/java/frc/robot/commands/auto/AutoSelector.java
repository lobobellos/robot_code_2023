package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drive.DriveForwards;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elbow;
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

  public AutoSelector(DriveBase db,Shoulder shoulder,Elbow elbow){

    this.db = db;
    this.shoulder = shoulder;
    this.elbow = elbow;

    autoChooser.addOption("right", AutoType.right);
    autoChooser.addOption("center", AutoType.center);
    autoChooser.addOption("left", AutoType.left);
    SmartDashboard.putData("Auto choices", autoChooser);

  }
  
  @Override
  public void initialize() {
    switch(  autoChooser.getSelected().name()){
      case "left":
      scheduler.schedule(new UnloadArm(shoulder, elbow));
      //scheduler.schedule(new DriveForwards(db, 32));
      break;
      case "center":
      scheduler.schedule(new DriveForwards(db, 3));
      break;
      case "right":
      scheduler.schedule(); //TODO: make third auto
      break;
    } 
    
  }
}
