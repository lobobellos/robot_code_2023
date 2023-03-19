package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveBase;

public class DriveForwards extends RunCommand{
  
  static PIDController controller = new PIDController(1, 0, 0.5);

  public DriveForwards(DriveBase db,Rotation2d setpoint){
    super(
      ()->{
        db.drive(
          0,
          0,
          controller.calculate(
            // TODO: get actual measurement
            0, //placeholder
            setpoint.getDegrees()
          )
        );
      }, 
      db
    );
    controller.setTolerance(1, 3);
  }
}
