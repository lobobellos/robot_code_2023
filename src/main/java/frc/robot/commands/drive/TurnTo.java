package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Const;
import frc.robot.subsystems.DriveBase;

public class TurnTo extends CommandBase{

  DriveBase db;

  PIDController controller = new PIDController(
    Const.drive.pidff.kP,
    Const.drive.pidff.kI,
    Const.drive.pidff.kD
  );

  double setpoint;

  public TurnTo(DriveBase db,double setpoint){
    this.db = db;

    addRequirements(db);
    

    controller.setTolerance(1, 3);
  }

  public void execute(){
    db.drive(
      0,
      0,
      controller.calculate(
        db.getAngle(),
        setpoint
      )
    );
  }

  public boolean isFinished(){
    return controller.atSetpoint();
  }
}
