package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.drive.Balance;
import frc.robot.commands.drive.DriveForwards;
import frc.robot.subsystems.*;

public class AutoSelector extends CommandBase {
  

  final SendableChooser<Command> autoChooser= new SendableChooser<Command>();

  final CommandScheduler scheduler=CommandScheduler.getInstance();

  Command driveLong, driveCenter, driveShort, noDrive;
  
  public AutoSelector(DriveBase db,Shoulder shoulder,Elbow elbow,Foot foot,Claw claw){
    
    driveLong = new SequentialCommandGroup(
      new InstantCommand(db::resetEncoders),
      new ScoreHigh(shoulder, elbow, claw, db),
      new DriveForwards(db,-120)
    );

    driveShort = new SequentialCommandGroup(
      new InstantCommand(db::resetEncoders),
      new ScoreHigh(shoulder, elbow, claw, db),
      new DriveForwards(db,-80)
    );

    driveCenter = new SequentialCommandGroup(
      new InstantCommand(db::resetEncoders),
      new ScoreHigh(shoulder, elbow, claw, db),
      new DriveForwards(db,-60),
      foot.setSolenoid(DoubleSolenoid.Value.kReverse)
    );

    noDrive = new SequentialCommandGroup(
      new ScoreHigh(shoulder, elbow, claw, db)
    );

    autoChooser.setDefaultOption("noDrive", noDrive);
    autoChooser.addOption("short", driveShort);
    autoChooser.addOption("center", driveCenter);
    autoChooser.addOption("long", driveLong);
    autoChooser.addOption("noDrive", noDrive);
    SmartDashboard.putData("Auto choices", autoChooser);

  }
  
  public Command getSelectedCommand() {
    return autoChooser.getSelected();
  }
}
