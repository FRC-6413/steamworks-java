package org.usfirst.frc6413.PowerUp.commands;

import org.usfirst.frc6413.PowerUp.Robot;
import org.usfirst.frc6413.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToSwitch extends Command {
	private PIDController pid;
	
	public DriveToSwitch(){		
		double distance = .2;
		
		requires(Robot.driveBase);
		double kP = -.4;
		double kI = 1;
		double kD = 5;
		
		pid = new PIDController(kP, kI, kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Robot.driveBase.getDistanceAhead();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double d) {
				Robot.driveBase.driveForward(d);
				System.out.println(d);
			}
		});
		pid.setAbsoluteTolerance(0.01);
		pid.setSetpoint(distance);
		pid.setOutputRange(0, .35);
		
		SmartDashboard.putData("driveToPeg", pid);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.ArmIsUp = false;
    	double distance = .2;
    	
    	pid.setAbsoluteTolerance(0.01);
		pid.setSetpoint(distance);
		pid.setOutputRange(0, .35);    	
    	
    	pid.reset();
		pid.enable();
		System.out.println("Staring drive to peg");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveBase.driveForward(.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//double distance = Robot.driveBase.getDistanceAhead();
    	//System.out.println(distance);
        //return distance < .48;
    	return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveBase.driveForward(0);
    	System.out.println("done with drive to peg");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
