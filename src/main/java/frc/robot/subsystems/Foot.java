package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Foot extends SubsystemBase{

  PneumaticsControlModule pcm = new PneumaticsControlModule(Const.pneumatics.PCMID);
  
  DoubleSolenoid mainSolenoid;

  public Foot(Pneumatics pneumatics){
    pcm = pneumatics.getPCM();

    mainSolenoid = pcm.makeDoubleSolenoid(
      Const.foot.doubleSolenoid.forwardChannel,
      Const.foot.doubleSolenoid.backwardChannel
    );

    mainSolenoid.set(DoubleSolenoid.Value.kForward);
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

  public void periodic(){
    SmartDashboard.putString("pneumatics",getStatus());
  }

}