package frc.robot.commands.auto;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunUntilStop extends CommandBase{

  Runnable toRun;
  BooleanSupplier toCancel;

  public RunUntilStop(Runnable toRun,BooleanSupplier toCancel){
    
    this.toRun = toRun;
    this.toCancel = toCancel;
    
  }

  public void execute(){
    toRun.run();
  }

  public boolean isFinished(){
    return toCancel.getAsBoolean();
  }
}
