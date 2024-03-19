// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final Shoulder shoulder = new Shoulder();
  private final DriveBase db = new DriveBase();
  // final Pneumatics pneumatics = new Pneumatics();
  // final Foot foot = new Foot(pneumatics);
  // final Shoulder shoulder = new Shoulder();
  // final Elbow elbow = new Elbow();
  // final Claw claw = new Claw();

  // final AutoSelector selector = new AutoSelector(db, shoulder, elbow, foot,
  // claw);

  private final CommandXboxController bodyController = new CommandXboxController(0);
  // private final CommandXboxController armController = new
  // CommandXboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // configure the camera server
    CameraServer.startAutomaticCapture();

    db.setDefaultCommand(
        new RunCommand(
            () -> db.drive(
                bodyController.getLeftY(),
                bodyController.getLeftX(),
                bodyController.getRightX(),
                false),
            db));

    // elbow.setDefaultCommand(
    // new RunCommand(
    // () -> {
    // elbow.runPID.run();
    // elbow.move(-armController.getRightY() / 3 * (1 +
    // armController.getRightTriggerAxis())).run();
    // SmartDashboard.putNumber("joysitck left", armController.getLeftY());
    // SmartDashboard.putNumber("joysitck right", armController.getRightY());
    // },
    // elbow));

    // shoulder.setDefaultCommand(
    // new RunCommand(
    // () -> {
    // shoulder.runPID.run();
    // shoulder.move(armController.getLeftY() / 30 * (1 +
    // armController.getLeftTriggerAxis())).run();
    // },
    // shoulder));

    // Configure the button bindings
    configureButtonBindings();

    // SmartDashboard.putData(shoulder);
    // SmartDashboard.putData(claw);
    SmartDashboard.putData(db);
    // SmartDashboard.putData(pneumatics);
    // SmartDashboard.putData(elbow);

  }

  private void configureButtonBindings() {

    // bodyController.b()
    // .onTrue(foot.toggleSolenoid());

    // bodyController.y()
    // .onTrue(pneumatics.toggleCompressor());

    // bodyController.leftBumper()
    // .whileTrue(new MoveHand(claw, MoveHand.position.closed));

    // bodyController.rightBumper()
    // .whileTrue(new MoveHand(claw, MoveHand.position.open));

    // bodyController.povDown()
    // .onTrue(new ReleaseAndZero(shoulder, elbow));

    bodyController.povUp()
        .onTrue(new InstantCommand(db.calibrate()));

    // back to turn off gyro
    bodyController.back()
        .onTrue(db.setWithGyro(false));

    // start to turn it on
    bodyController.start()
        .onTrue(db.setWithGyro(true));

    // armController.start()
    // .onTrue(new ReleaseAndZero(shoulder, elbow));

    // armController.leftBumper()
    // .whileTrue(new MoveHand(claw, MoveHand.position.closed));

    // armController.rightBumper()
    // .whileTrue(new MoveHand(claw, MoveHand.position.open));

    // armController.povUp()
    // .onTrue(shoulder.moveUp());

    // armController.povDown()
    // .onTrue(shoulder.moveDown());

    // armController.a()
    // .onTrue(elbow.moveDown());

    // armController.y()
    // .onTrue(elbow.moveUp());

    // armController.b()
    // .onTrue(new SequentialCommandGroup(
    // // shoulder.moveTo(-3.5),
    // // elbow.moveTo(20)
    // ));
  }

  public Command getAutonomousCommand() {
    // return selector.getSelectedCommand();
    return Commands.print("theres no auto, buddy");
  }
}
