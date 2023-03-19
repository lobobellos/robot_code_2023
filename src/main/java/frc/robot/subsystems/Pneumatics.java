package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class Pneumatics extends SubsystemBase{

  PneumaticsControlModule pcm = new PneumaticsControlModule(Const.pneumatics.PCMID);
  
  Compressor comp;

  public Pneumatics(){
    comp = pcm.makeCompressor();
    comp.enableDigital();
  }

  public void enable(){
    comp.enableDigital();
  }

  public void disable(){
    comp.disable();
  }

  public InstantCommand toggleCompressor(){
    return new InstantCommand(
      ()->{
        if(comp.isEnabled()){
          comp.disable();
        }else{
          comp.enableDigital(); 
        } 
      }
    );
  }

  public PneumaticsControlModule getPCM(){
    return pcm;
  }

}