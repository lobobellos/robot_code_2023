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
  
  final SendableChooser<Integer> autoChooser= new SendableChooser<Integer>();

  public AutoSelector(DriveBase db,Shoulder shoulder,Elbow elbow){
    CommandScheduler scheduler  =CommandScheduler.getInstance();

    autoChooser.addOption("right", Integer.valueOf(0));
    autoChooser.addOption("center", Integer.valueOf(1));
    autoChooser.addOption("left", Integer.valueOf(2));
    SmartDashboard.putData("Auto choices", autoChooser);

    switch( (int) autoChooser.getSelected()){
      case 0:
      scheduler.schedule(new UnloadArm(shoulder, elbow));
      //scheduler.schedule(new DriveForwards(db, 32));
      break;
      case 1:
      scheduler.schedule(new DriveForwards(db, 3));
      break;
      case 2:
      scheduler.schedule(); //TODO: make third auto
      break;

    } 
  }
}
