package org.usfirst.frc6413.SteamWorks;

import edu.wpi.first.wpilibj.buttons.Button;

public class GearSensorDigitalSwitch extends Button {

	@Override
	public boolean get() {
		return RobotMap.armGearTrigger.get();
	}

}
