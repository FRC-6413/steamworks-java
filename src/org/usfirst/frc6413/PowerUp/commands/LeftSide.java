package org.usfirst.frc6413.PowerUp.commands;

import org.usfirst.frc6413.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSide extends CommandGroup {
	
	public LeftSide() {
		/*String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
    	System.out.println("FMS: " + gameData);*/
		//addSequential(new DriveToSwitch());
		addSequential(new ExpelBox());
		RobotMap.LeftSideStart = true;		
	}

}
