package frc.robot.commands.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
      ()->{
        db.drive(
          -MathUtil.clamp(
            controller.calculate(
              db.getAverageDist(),
              setpoint
            ), 
            -0.4,
            0.4
          ),
          0,
          0,
          false
        );
      },
      db
    );
    controller.setTolerance(0.1,0.1);
    db.resetEncoders();
    SmartDashboard.putNumber("db setpoint",setpoint);
  }

  @Override
  public boolean isFinished() {
    return controller.atSetpoint();
  }
}
