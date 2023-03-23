package frc.robot.commands.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Const;
import frc.robot.subsystems.DriveBase;

public class Balance  extends RunCommand{

  static PIDController controller = new PIDController(
    Const.drive.pidff.kP,
    Const.drive.pidff.kI,
    Const.drive.pidff.kD
  );

  static double setpoint = 0;

  public Balance(DriveBase db){
    super(
      ()->{
        db.drive(
          -MathUtil.clamp(
            controller.calculate(
              db.getAngleY().getDegrees(),
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
  }
}
