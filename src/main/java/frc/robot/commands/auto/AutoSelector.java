package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveBase;

public class AutoSelector extends CommandBase {
  public AutoSelector(DriveBase db){
    CommandScheduler scheduler  =CommandScheduler.getInstance();

    int selected = (int) SmartDashboard.getNumber("auto selector", 0);

    switch(selected){
      case 0:
      scheduler.schedule(new DriveForwards(db, 5));
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
