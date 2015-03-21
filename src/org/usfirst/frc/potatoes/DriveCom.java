package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCom extends Command {
	Omni omni;
    public DriveCom(Omni omni) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		  this.omni = omni;
    		  setTimeout(.6);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	omni.drive(1,0,0);
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	omni.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
