package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveBase;

public class TurnTo extends RunCommand{

  DriveBase db;

  static PIDController controller = new PIDController(
    0.009,
    0.7e-2,
    0.009
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
          ),
          false
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
