// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Const {
  //drive motors
  
  public static final class drive{
    public static final int fl=4;
    public static final int fr=1;
    public static final int rl=2;
    public static final int rr=3;
    public static final double rotateSensitivity = 0.5;
  }
  
  public static final double  michaelsAge = 4.9679834928365;

  //claw arm

  public static final class Shoulder{
    public static final int ID_L = 8;
    public static final int ID_R = 6;


    
    //pid
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;

    //feed fowrard control
    public static final double kG = 0;
    public static final double kV = 0;

    public static final double tolerance = 0;
    
  }

  public static final class Elbow{
    public static final int ID = 7;
    
  }
  
  public static final class PDP{
    public static final int ID = 10;
  }
  
  public static final class claw{
    public static final int ID = 11;
  }


  public static class pneumatics{
    public static final int PCMID = 19;
  }
  public static class foot{

    public static class doubleSolenoid{
      public static final int forwardChannel = 1;
      public static final int backwardChannel = 0;
    }
  }


}
