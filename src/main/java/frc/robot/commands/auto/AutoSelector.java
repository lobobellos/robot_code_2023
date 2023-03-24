package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.drive.Balance;
import frc.robot.commands.drive.DriveForwards;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elbow;
import frc.robot.subsystems.Foot;
import frc.robot.subsystems.Shoulder;

public class AutoSelector extends CommandBase {
  
  public static enum AutoType{
    longer,
    shorter,
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

    autoChooser.setDefaultOption("long", AutoType.longer);
    autoChooser.addOption("short", AutoType.shorter);
    autoChooser.addOption("center", AutoType.center);
    autoChooser.addOption("long", AutoType.longer);
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
        
          new DriveForwards(db,100)
        )
      );
    }else if(name == AutoType.center){
      scheduler.schedule(
        new SequentialCommandGroup(
          foot.setSolenoid(DoubleSolenoid.Value.kReverse), 
          //new DriveForwards(db, 50),
          new UnloadArm(shoulder, elbow)
        )
      );
    }else if(name ==AutoType.shorter){
      scheduler.schedule(
        new SequentialCommandGroup(
          new DriveForwards(db,50)
        )
      ); 
    }    
  }
}
