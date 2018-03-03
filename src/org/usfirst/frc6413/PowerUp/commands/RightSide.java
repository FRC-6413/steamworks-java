package org.usfirst.frc6413.PowerUp.commands;

import org.usfirst.frc6413.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSide extends CommandGroup {
	
	public RightSide() {
		//addSequential(new DriveToSwitch());
		addSequential(new ExpelBox());
		RobotMap.LeftSideStart = false;
	}

}
