package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Const;
import frc.robot.subsystems.DriveBase;

public class DriveForwards extends RunCommand{

  static PIDController controller = new PIDController(
    Const.drive.pidff.kP,
    Const.drive.pidff.kI,
    Const.drive.pidff.kD
  );

  public DriveForwards(DriveBase db,double setpoint){
    super(
      ()->db.drive(
        controller.calculate(
          db.getAverageDist(),
          setpoint),
          0,
          db.getAngleZ().getDegrees()/360), 
      db
    );
  }
}
