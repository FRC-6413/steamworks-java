package org.usfirst.frc6413.PowerUp.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSide extends CommandGroup {
	
	public LeftSide() {
		
		String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
    	System.out.println("FMS: " + gameData);
        
        if(gameData.charAt(0) == 'L') {
        	System.out.println("ShiftHigh");
        	addSequential(new ShiftHighGear());
        } else if (gameData.charAt(0) == 'R') {
        	System.out.println("ShiftLow");
        	addSequential(new ShiftLowGear());
        }
	}
	
	@Override
	protected void execute() {
		
	}

}
