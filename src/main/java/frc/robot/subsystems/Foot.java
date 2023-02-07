package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
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

}