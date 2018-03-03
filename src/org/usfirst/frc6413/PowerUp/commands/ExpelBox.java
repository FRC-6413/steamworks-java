package org.usfirst.frc6413.PowerUp.commands;

import org.usfirst.frc6413.PowerUp.Robot;
import org.usfirst.frc6413.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ExpelBox extends Command {
	
	public ExpelBox() {
		requires(Robot.boxIntake);
	}
	
	protected void initialize() {
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    if(RobotMap.LeftSideStart) {
	        if(RobotMap.gameData.charAt(0) == 'L') {
	        	System.out.println("Correct side");
	        } else if (RobotMap.gameData.charAt(0) == 'R') {
	        	System.out.println("Incorrect Side");
	        }
    	} else {
    		if(RobotMap.gameData.charAt(0) == 'L') {
	        	System.out.println("Incorrect Side");
	        } else if (RobotMap.gameData.charAt(0) == 'R') {
	        	System.out.println("Correct side");
	        }
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return false;
	}

}
