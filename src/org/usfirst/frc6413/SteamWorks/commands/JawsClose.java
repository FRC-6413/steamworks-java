// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc6413.SteamWorks.commands;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6413.SteamWorks.Robot;
import org.usfirst.frc6413.SteamWorks.RobotMap;

/**
 *
 */
public class JawsClose extends Command {

	//private boolean armIsOpen = true;
	private boolean armDebounce = false;
	//private long closeTime;
	
	private boolean fromSwitch = true;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public JawsClose(boolean fromSwitches) {
    	this.fromSwitch = fromSwitches;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	System.out.println("Switch: " + fromSwitch);
    	if(!RobotMap.JawsClosed && !RobotMap.ArmDownDebounce) {
    		if((fromSwitch && RobotMap.ArmIsUp) || !fromSwitch) {
    			setTimeout(.5);
        		
        		Robot.arm.closeArm();
        		Robot.oi.driveJoystick.setRumble(RumbleType.kLeftRumble, 1);
        		Robot.oi.armJoystick.setRumble(RumbleType.kLeftRumble, 1);
        		RobotMap.JawsClosed = !RobotMap.JawsClosed;
    		}
    		
    		
    	} else if(fromSwitch == false) {
    		setTimeout(2);
    		RobotMap.ArmDownDebounce = true;
    		Robot.arm.openArm();
    		RobotMap.JawsClosed = !RobotMap.JawsClosed;
    	}  
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	  	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {     	
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.driveJoystick.setRumble(RumbleType.kLeftRumble,0);
		Robot.oi.armJoystick.setRumble(RumbleType.kLeftRumble, 0);
    	RobotMap.ArmDownDebounce = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
