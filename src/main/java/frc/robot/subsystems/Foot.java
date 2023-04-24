package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Foot extends SubsystemBase{

  PneumaticsControlModule pcm;
  
  DoubleSolenoid mainSolenoid;

  public Foot(Pneumatics pneumatics){
    pcm = pneumatics.getPCM();

    mainSolenoid = pcm.makeDoubleSolenoid(
      Const.foot.doubleSolenoid.forwardChannel,
      Const.foot.doubleSolenoid.backwardChannel
    );

    mainSolenoid.set(DoubleSolenoid.Value.kForward);

    addChild("mainSolenoid", mainSolenoid);
    setName("Foot");
  }


  public InstantCommand toggleSolenoid(){
    return new InstantCommand(
      ()->mainSolenoid.toggle()
    );
  }

  public InstantCommand setSolenoid(DoubleSolenoid.Value val){
    return new InstantCommand(
      ()->mainSolenoid.set(val)
    );
  }

  public String getStatus(){
    return mainSolenoid.get().name();
  }
}