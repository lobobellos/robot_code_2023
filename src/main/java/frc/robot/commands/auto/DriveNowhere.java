package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveBase;

public class DriveNowhere extends RunCommand {
  public DriveNowhere(DriveBase db){
    super(
      ()->db.drive(0, 0, 0, false),
      db
    );
  }
}
