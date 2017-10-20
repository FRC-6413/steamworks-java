package org.usfirst.frc6413.SteamWorks.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6413.SteamWorks.Robot;
import org.usfirst.frc6413.SteamWorks.RobotMap;

public class OpenJaws extends Command {

	public OpenJaws() {
		requires(Robot.arm);
	}
	
	
	protected void initialize() {    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	  	Robot.arm.openArm();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {     	
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
