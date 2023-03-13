package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoulder;

public class MoveShoulderTo extends CommandBase{

  Shoulder shoulder;

  public MoveShoulderTo(Shoulder shoulder,double setpoint){


    addRequirements(shoulder);

  }


}
