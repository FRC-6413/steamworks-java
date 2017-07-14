package org.usfirst.frc6413.SteamWorks.commands;

import org.usfirst.frc6413.SteamWorks.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToPeg extends Command {
	
	public DriveToPeg(){
		
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("starting drive forward!");
    	Robot.driveBase.driveForward(.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.driveForward(.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double distance = Robot.driveBase.getDistanceAhead();
    	System.out.println(distance);
        return distance < .48;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.driveForward(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
