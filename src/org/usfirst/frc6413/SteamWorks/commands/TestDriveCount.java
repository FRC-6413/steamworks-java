package org.usfirst.frc6413.SteamWorks.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6413.SteamWorks.Robot;
import org.usfirst.frc6413.SteamWorks.RobotMap;

public class TestDriveCount extends Command {

	double initialCount = 0;
	double countToDrive = 2;
	
	public TestDriveCount() {
		requires(Robot.driveBase);
	}
	
	protected void initialize() {
		setTimeout(15);
		initialCount = RobotMap.driveBaseLFM.getPosition();
		//Robot.driveBase.driveForward(.25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double position = RobotMap.driveBaseLFM.getPosition();
    	System.out.println(position);
    	Robot.driveBase.driveForward(.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double position = RobotMap.driveBaseLFM.getPosition();
        return isTimedOut() || initialCount + countToDrive < position;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.driveForward(0);
    	
    	System.out.println("Initial: " + initialCount + " - Final: " + RobotMap.driveBaseLFM.getPosition());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
