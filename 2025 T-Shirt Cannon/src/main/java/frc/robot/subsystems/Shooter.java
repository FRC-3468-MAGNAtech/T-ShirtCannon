// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public PneumaticHub hubR;
  public Compressor compR;
  public Solenoid solenoidR; 
  public AnalogInput sensorR;
  public PneumaticHub hubL;
  public Compressor compL;
  public Solenoid solenoidL; 
  public AnalogInput sensorL;

  public double setPressure = ShooterConstants.shortFire;
  public boolean fireSide = false;

  public Shooter() {
      
    // right side
    hubR = new PneumaticHub(ShooterConstants.hubR);
    compR = new Compressor(ShooterConstants.hubR, PneumaticsModuleType.REVPH);
    compR.enableAnalog(60.0, 70.0);
    solenoidR = new Solenoid(ShooterConstants.hubR, PneumaticsModuleType.REVPH, 0);

    
    
    // left side
    hubL = new PneumaticHub(ShooterConstants.hubL);
    compL = new Compressor(ShooterConstants.hubL, PneumaticsModuleType.REVPH);
    compL.enableAnalog(60.0, 70.0);
    solenoidL = new Solenoid(ShooterConstants.hubL, PneumaticsModuleType.REVPH, 0);
  }

  public void setPressuerVal(double pressure) {
    setPressure = pressure;
    compR.enableAnalog(setPressure - 10.0, setPressure);
    compL.enableAnalog(setPressure - 10.0, setPressure);
  }

  public void leftFire() {solenoidL.set(true);}
  
  public void rightFire() {solenoidR.set(true);}

  public void closeSolenoids() {
    solenoidL.set(false);
    solenoidR.set(false);
  }

  public void switchTanks() {
    fireSide = !fireSide;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Comp Left On", compL.isEnabled());
    SmartDashboard.putBoolean("Comp Right On", compR.isEnabled());

    SmartDashboard.putNumber("Left Pressure", hubL.getPressure(0));
    SmartDashboard.putNumber("RIght Pressure", hubR.getPressure(0));

    SmartDashboard.putNumber("Set Pressure", setPressure);
    SmartDashboard.putBoolean("Firing Left Side", fireSide);
  }
}
