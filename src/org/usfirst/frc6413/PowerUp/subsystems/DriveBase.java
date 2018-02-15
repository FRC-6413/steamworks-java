// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc6413.PowerUp.subsystems;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.usfirst.frc6413.PowerUp.RobotMap;
import org.usfirst.frc6413.PowerUp.commands.MecanumDriveClass;
import org.usfirst.frc6413.PowerUp.commands.ShiftHighGear;
import org.usfirst.frc6413.PowerUp.commands.ShiftLowGear;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveBase extends Subsystem implements PIDOutput {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final WPI_TalonSRX lFM = RobotMap.driveBaseLFM;
	private final WPI_TalonSRX lRM = RobotMap.driveBaseLRM;
	private final WPI_TalonSRX lMM = RobotMap.driveBaseLMM;
	private final WPI_TalonSRX rFM = RobotMap.driveBaseRFM;
	private final WPI_TalonSRX rRM = RobotMap.driveBaseRRM;
	private final WPI_TalonSRX rMM = RobotMap.driveBaseRMM;
	private final DifferentialDrive robotDrive41 = RobotMap. driveBaseRobotDrive41;
	//private final AnalogInput rearUltrasonic = RobotMedu.wpi.first.wpilibj.drive.MecanumDriveap.driveBaseRearUltrasonic;
	private final AnalogInput frontUltrasonic = RobotMap.driveBaseFrontUltrasonic;
	
	private final DoubleSolenoid shifterSolenoid = RobotMap.shifterSolenoid;
	double rotateToAngleRate;
	
	AHRS ahrs;
	PIDController turnController;
	
	static final double kP = 0.022;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    
    static final double kToleranceDegrees = 1.0f;    
    
    static final double kTargetAngleDegrees = 90.0f;
    
    double rightStickValue;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public DriveBase() {
    	try {
			/***********************************************************************
			 * navX-MXP:
			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro:
			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            //DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
        turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-0.5, 0.5);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.disable();
    }

	

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new MecanumDriveClass());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void arcadeDriveMethod(XboxController controller) {		
		
	  	//new StringBuilder();
		double x = deadZoneInput(controller.getX(GenericHID.Hand.kRight), 0.3) * 1;
		double y = deadZoneInput(controller.getY(GenericHID.Hand.kLeft), 0.1) * -1;
		
		//robotDrive41.arcadeDrive(y, rightStickValue);
		robotDrive41.arcadeDrive(y, x);			
	}

	public void driveCount() {
		lFM.set(ControlMode.Position, 1000);
	}
	
	public void driveForward(double speed) {
		// need to reverse the right side, due to setup of mecanum drive
		/*lFM.set(ControlMode.PercentOutput, speed);
		
		rFM.set(ControlMode.PercentOutput, speed);
		
		lRM.set(ControlMode.PercentOutput, speed);
		
		rRM.set(ControlMode.PercentOutput, speed);
		
		lMM.set(ControlMode.PercentOutput, speed);
		
		rMM.set(ControlMode.PercentOutput, speed);*/ 
		robotDrive41.arcadeDrive(speed, 0);
	}

	//public double getDistanceBehind() {
	//	return rearUltrasonic.getAverageVoltage();
	//}

	public double getDistanceAhead() {
		return frontUltrasonic.getAverageVoltage();
	}

	public void rotateToDegree(double degree) {

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	private double deadZoneInput(double input, double deadZone) {
		if (input <= deadZone && input >= -deadZone)
			return 0;
		else if (input >= deadZone)
			input = ((input - deadZone) / (1 - deadZone));
		else if (input <= deadZone)
			input = ((-input - deadZone) / (deadZone - 1));

		return round(input, 2);
	}
	
	public void shiftLowGear() {
		//Low Gear
		shifterSolenoid.set(Value.kForward);
		System.out.println("Shifting To Low Gear");
	}
	
	public void shiftHighGear() {
		//High Gear
		shifterSolenoid.set(Value.kReverse);
		System.out.println("Shifting To High Gear");
	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
	
	public void leftSide() {
		//1/27/2018 Change later
		new ShiftLowGear();
		
	}
	
	public void rightSide() {
		new ShiftHighGear();
	}
	
}
