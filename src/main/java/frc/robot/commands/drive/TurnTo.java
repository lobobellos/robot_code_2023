package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Const;
import frc.robot.subsystems.DriveBase;

public class TurnTo extends RunCommand{

  DriveBase db;

  static PIDController controller = new PIDController(
    Const.drive.pidff.kP,
    Const.drive.pidff.kI,
    Const.drive.pidff.kD
  );

  Rotation2d setpoint;

  public TurnTo(DriveBase db,Rotation2d setpoint){
    super(
      ()->{
        db.drive(
          0,
          0,
          controller.calculate(
            db.getAngleZ().getDegrees(),
            setpoint.getDegrees()
          )
        );
      }, 
      db
    );
    controller.setTolerance(1, 3);
  }

  public boolean isFinished(){
    return controller.atSetpoint();
  }
}
