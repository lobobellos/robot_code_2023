// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Shoulder shoulder = new Shoulder();
private final DriveBase db = new DriveBase();
  final Pneumatics pneumatics = new Pneumatics();
  final Foot foot = new Foot(pneumatics);
  final Shoulder shoulder= new Shoulder();
  final Elbow elbow = new Elbow();
  final Gyro gyro = new Gyro();
  final Claw claw = new Claw();

  private final CommandXboxController controller = new CommandXboxController(1);
  
  double shouderSetpoint = -1;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CameraServer.startAutomaticCapture();

    db.setDefaultCommand(
      new RunCommand(
        ()->{
          db.drive(controller.getLeftY(),controller.getLeftX(),controller.getRightX());
          SmartDashboard.putBoolean("isDriving", true);
        },
        db
      )
    );
    

    shoulder.setDefaultCommand(
      new MoveShoulderTo(shoulder,()->shouderSetpoint )
    );

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    controller.a().onTrue(new InstantCommand(
      ()->{
        foot.set(DoubleSolenoid.Value.kForward);
      },
      foot
    ));
    
    controller.b()
    .onTrue(new InstantCommand(
      ()->foot.set(DoubleSolenoid.Value.kReverse),
      foot
    ));
    
    controller.x()
    .onTrue(new InstantCommand(
      ()->foot.set(DoubleSolenoid.Value.kOff),
      foot
    ));

    controller.y()
    .onTrue(new InstantCommand(
      ()->pneumatics.toggleDisabled(),
      pneumatics
    ));

    controller.leftBumper()
    .whileTrue(
      new MoveHand(claw, MoveHand.position.closed)
    );

    controller.rightBumper()
    .whileTrue(
      new MoveHand(claw, MoveHand.position.open)
    );

    controller.start()
    .onTrue(
      new ResetEncoders(shoulder, elbow, claw)
    );
    
    controller.povUp()
    .onTrue(new InstantCommand(
      ()->{
        shouderSetpoint -= 1;
        if(shouderSetpoint == -5){ 
          shouderSetpoint = -4;
        }
      }
    ));

    controller.povDown()
    .onTrue(new InstantCommand(
      ()->{
        shouderSetpoint += 1;
        if(shouderSetpoint == 0){ 
          shouderSetpoint = -1;
        }
      }
    ));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
