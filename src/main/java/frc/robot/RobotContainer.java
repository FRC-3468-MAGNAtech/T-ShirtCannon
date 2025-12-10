// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.FireCannon;
import frc.robot.commands.ManualPressure;
import frc.robot.commands.SetTankPressure;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final DriveTrain m_driveTrain = new DriveTrain();
    private final Shooter m_shooter = new Shooter();
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    
    private final XboxController m_driverController = new XboxController(OperatorConstants.kDriverControllerPort);
    
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
    }
    
    private void configureBindings() {

        // buttons
        JoystickButton shortFire = new JoystickButton(m_driverController, OperatorConstants.shortButton);
        JoystickButton medFire = new JoystickButton(m_driverController, OperatorConstants.medButton);
        JoystickButton longFire = new JoystickButton(m_driverController, OperatorConstants.longButton);
        JoystickButton fire = new JoystickButton(m_driverController, OperatorConstants.fireButton);
        JoystickButton safeFire = new JoystickButton(m_driverController, OperatorConstants.safeFireButton);
        POVButton manualUp = new POVButton(m_driverController, OperatorConstants.manualUpButton);
        POVButton manualDown = new POVButton(m_driverController, OperatorConstants.manualDownButton);
        
        // Controls
        m_driveTrain.setDefaultCommand(
            new RunCommand( () -> m_driveTrain.arcadeDrive(
                    -m_driverController.getLeftY(), -m_driverController.getRightX()),
                    m_driveTrain));
        
        shortFire.onTrue(new SetTankPressure(m_shooter, ShooterConstants.shortFire));
        medFire.onTrue(new SetTankPressure(m_shooter, ShooterConstants.medFire));
        longFire.onTrue(new SetTankPressure(m_shooter, ShooterConstants.longFire));
        manualUp.onTrue(new ManualPressure(m_shooter, true));
        manualDown.onTrue(new ManualPressure(m_shooter, false));
        

        // if (rightPressureVesselStorageUnitOnRobot){
        //     fire.and(safeFire).whileTrue(new SequentialCommandGroup(
        //     Commands.waitSeconds(2),
        //     new FireLeft(m_shooter).withTimeout(.4), 
        //     new InstantCommand(() -> rightPressureVesselStorageUnitOnRobot = false)));
        // }
        // else {
        //     fire.and(safeFire).whileTrue(new SequentialCommandGroup(
        //     Commands.waitSeconds(2),
        //     new FireRight(m_shooter).withTimeout(.4),
        //     new InstantCommand(() -> rightPressureVesselStorageUnitOnRobot = true)));
        // }
        fire.and(safeFire).whileTrue(new SequentialCommandGroup(
            Commands.waitSeconds(2),
            new FireCannon(m_shooter).withTimeout(.4),
            Commands.waitSeconds(2)));
    }

    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
