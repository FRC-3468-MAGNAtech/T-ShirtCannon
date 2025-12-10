// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

DifferentialDrive m_drivetrain;

public DriveTrain() {
    SparkMax leftFront = new SparkMax(DriveConstants.FrontLeftID, MotorType.kBrushless);
    SparkMax leftBack = new SparkMax(DriveConstants.BackLeftID, MotorType.kBrushless);
    SparkMax rightFront = new SparkMax(DriveConstants.FrontRightID, MotorType.kBrushless);
    SparkMax rightBack = new SparkMax(DriveConstants.BackRightID, MotorType.kBrushless);

    SparkMaxConfig currentLimitConfig = new SparkMaxConfig();
    currentLimitConfig.smartCurrentLimit(40);
    currentLimitConfig.voltageCompensation(12);
    currentLimitConfig.follow(DriveConstants.FrontLeftID, false);
    currentLimitConfig.follow(DriveConstants.BackLeftID, false);
    currentLimitConfig.follow(DriveConstants.FrontRightID, false);
    currentLimitConfig.follow(DriveConstants.BackRightID, false);

    SparkMaxConfig leftDriveConf = new SparkMaxConfig();
    leftDriveConf.follow(DriveConstants.FrontLeftID, false);
    leftBack.configure(leftDriveConf, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    SparkMaxConfig rightDriveConf = new SparkMaxConfig();
    rightDriveConf.follow(DriveConstants.FrontRightID, false);
    rightBack.configure(rightDriveConf, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    m_drivetrain = new DifferentialDrive(leftFront, rightFront);
}

public void arcadeDrive(double speed, double rotation){
    m_drivetrain.arcadeDrive(speed, rotation);
}

public void stop(){
    m_drivetrain.arcadeDrive(0,0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
