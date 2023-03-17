package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Shoulder;

public class MoveShoulderTo extends RunCommand{

  //horisontal is -2.8 
  public MoveShoulderTo(Shoulder shoulder,DoubleSupplier setpoint){
    super(
      ()->shoulder.setSetPoint(setpoint.getAsDouble()),
      shoulder
    );
  }
}
