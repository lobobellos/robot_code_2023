package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  }

  public void stop(){
    mainSolenoid.set(DoubleSolenoid.Value.kOff);
  }

  public void set(DoubleSolenoid.Value val){
    mainSolenoid.set(val);
  }

  public String getStatus(){
    return mainSolenoid.get().name();
  }

  public void periodic(){
    SmartDashboard.putString("pneumatics",getStatus());
  }

}