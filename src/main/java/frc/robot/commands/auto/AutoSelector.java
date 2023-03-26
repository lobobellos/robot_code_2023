package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.drive.Balance;
import frc.robot.commands.drive.DriveForwards;
import frc.robot.subsystems.*;

public class AutoSelector extends CommandBase {
  
  public static enum AutoType{
    longer,
    shorter,
    center,
    noDrive
  }

  final SendableChooser<AutoType> autoChooser= new SendableChooser<AutoType>();

  final CommandScheduler scheduler=CommandScheduler.getInstance();

  final DriveBase db;
  final Shoulder shoulder;
  final Elbow elbow;
  final Foot foot;
  final Claw claw;

  public AutoSelector(DriveBase db,Shoulder shoulder,Elbow elbow,Foot foot,Claw claw){
    this.db = db;
    this.shoulder = shoulder;
    this.elbow = elbow;
    this.foot = foot;
    this.claw = claw;

    autoChooser.setDefaultOption("long", AutoType.longer);
    autoChooser.addOption("short", AutoType.shorter);
    autoChooser.addOption("center", AutoType.center);
    autoChooser.addOption("long", AutoType.longer);
    autoChooser.addOption("noDrive", AutoType.noDrive);
    SmartDashboard.putData("Auto choices", autoChooser);

  }
  
  @Override
  public void initialize() {

    SmartDashboard.putString("selected auto", autoChooser.getSelected().name());
    db.resetEncoders();

    AutoType name =  autoChooser.getSelected();

    if(name == AutoType.longer){
      scheduler.schedule(
        new SequentialCommandGroup(
          new ScoreHigh(shoulder, elbow, claw, db),
          new DriveForwards(db,-120)
        )
      );
    }else if(name == AutoType.center){
      scheduler.schedule(
        new SequentialCommandGroup(
          new ScoreHigh(shoulder, elbow, claw, db),
          new DriveForwards(db,-60),
          foot.setSolenoid(DoubleSolenoid.Value.kReverse)
        )
      );
    }else if(name ==AutoType.shorter){
      scheduler.schedule(
        new SequentialCommandGroup(
          new ScoreHigh(shoulder, elbow, claw, db),
          new DriveForwards(db,-80)
        )
      ); 
    }else if(name ==AutoType.noDrive){
      scheduler.schedule(
        new SequentialCommandGroup(
          new ScoreHigh(shoulder, elbow, claw, db)
        )
      ); 
    }  
  }
}
