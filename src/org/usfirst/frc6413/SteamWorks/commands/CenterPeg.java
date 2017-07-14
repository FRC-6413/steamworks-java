package org.usfirst.frc6413.SteamWorks.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPeg extends CommandGroup {

	public CenterPeg() {
		addSequential(new DriveToPeg());
	}
}
