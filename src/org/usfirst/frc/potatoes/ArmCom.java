package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmCom extends Command {
    Arm arm;
    public ArmCom(Arm arm) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.arm=arm;
    	setTimeout(.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.move(true,false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.move(false,false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
