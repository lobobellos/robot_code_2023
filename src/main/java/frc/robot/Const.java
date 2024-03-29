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

    public static final class pidff {
      //pid
      public static final double kP = 0.05;
      public static final double kI = 0;
      public static final double kD = 0.002;
      // I-zone
      public static final double kIz = 1;
      //feed forward control
      public static final double kS = 0;
      public static final double kV = 0;
      //output clamps
      public static final double minOutput = -1;
      public static final double maxOutput = 1;
    }
  }
  
  public static final double  michaelsAge = 4.9679834928365;

  //claw arm

  public static final class shoulder{
    public static final class ids{
      public static final int rl = 7;
      public static final int fl = 8;
      public static final int fr = 6;
      public static final int rr = 5;
      
    }

    //pid+ff
    public static final class pidff {
      //pid
      public static final double kP = 0.2;
      public static final double kI = 1e-4;
      public static final double kD = 1;
      // I-zone
      public static final double kIz = 1;
      //feed forward control
      public static final double kFF = 0;
      //output clamps
      public static final double minOutput = -1;
      public static final double maxOutput = 1;
    }

    public static final double tolerance = 0;
    
  }

  public static final class elbow{
    public static final int motorID = 9;
    public static final int servoID = 0;

    //pid+ff
    public static final class pidff {
      //pid
      public static final double kP = 1.5;
      public static final double kI = 0;
      public static final double kD = 0.1;
      // I-zone
      public static final double kIz = 0;
      //feed forward control
      public static final double kFF = 0;
      //output clamps
      public static final double minOutput = -1;
      public static final double maxOutput = 1;
    }
  }
  
  public static final class PDP{
    public static final int ID = 10;
  }
  
  public static final class claw{
    public static final int ID = 11;
    public static final double speed = 0.9;

    public static final double startPoint = 0;
    public static final double endPoint = 2100;


    //pid+ff
    public static final class pidff {
      //pid
      public static final double kP = 0.005;
      public static final double kI = 0;
      public static final double kD = 0.0005;
      // I-zone
      public static final double kIz = 1;
      //feed forward control
      public static final double kS = 0;
      public static final double kV = 0;
      //output clamps
      public static final double minOutput = -1;
      public static final double maxOutput = 1;
    }
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
