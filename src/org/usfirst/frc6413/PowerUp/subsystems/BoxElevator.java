package org.usfirst.frc6413.PowerUp.subsystems;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.usfirst.frc6413.PowerUp.RobotMap;
import org.usfirst.frc6413.PowerUp.commands.MecanumDriveClass;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxElevator extends Subsystem {

	VictorSP elevatorController = RobotMap.ElevatorController;
	Encoder elevatorEncoder = RobotMap.ElevatorEncoder;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new org.usfirst.frc6413.PowerUp.commands.MoveElevator());
	}
	
	public void MoveElevator(XboxController controller)
	{
		
		int encoderCount = elevatorEncoder.get();
		System.out.println(encoderCount);
		if(encoderCount < 500 && encoderCount >= 0) {
			
			double speed = deadZoneInput(controller.getY(GenericHID.Hand.kRight), 0.3)*-1;
			
			elevatorController.set(speed);
		}
		
		/*
		while(encoderCount < 500) {
			elevatorController.set(0.5);
		}
		
		elevatorController.set(0.0);
		*/
		
		
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
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
