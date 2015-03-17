package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * 
 * @author pltwe5
 *
 */
public class Omni {
	private SpeedController talonA, talonB,
				            talonC, talonD;

	double factor = 1.0;
	
	/**
	 * Recieves input for the gyro and talon ports.
	 * @param frontLeftPort
	 * @param frontRightPort
	 * @param backLeftPort
	 * @param backRightPort
	 */
	public Omni(int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort){
		 this(new Talon(frontLeftPort), new Talon(frontRightPort), new Talon(backLeftPort), new Talon(backRightPort));/*this() refers to the object being called*/
	} 
	
	/**
	 * Constructor for omni that takes the thing thing and sets it equal to the thing thing.
	 * @param frontLeft Talon in the front left part of the robot.
	 * @param frontRight Talon in the front right part of the robot.
	 * @param backLeft Talon in the back left part of the robot.
	 * @param backRight Talon in the back right part of the robot.
	 */
	public Omni(SpeedController frontLeft, SpeedController frontRight,
						   SpeedController backLeft,  SpeedController backRight){/*recieves input for the gyro and talon ports*/
		 talonA = frontLeft;
		 talonB = frontRight;
		 talonC = backLeft;
		 talonD = backRight;
	}
    
	/**
	 * Method that takes the desired speed for each direction and sets each talon to allow robot to go that way. All the 
	 * confusing math allows the robot to go directly forwards or sideways rather than strafing.
	 * @param x Desired speed in the x direction.
	 * @param y Desired speed in the y direction.
	 * @param z Desired rotation.
	 */
	public void drive(double x, double y, double z){
		double radians = - .783; //created new value to get the gyro angle, change to radians, and subtract .783
		double xPrime =  (y*Math.sin(radians)-(x*Math.cos(radians)));
    	double yPrime = -(y*Math.cos(radians)+(x*Math.sin(radians)));
    	
		talonA.set((( xPrime) + z)*factor);//set() sets pwm values. anywhere between -1 and 1
    	talonD.set(((-xPrime) + z)*factor);
    	talonB.set((( yPrime) + z)*factor);
    	talonC.set(((-yPrime) + z)*factor);
       }       
}
    	



/* TODO:
 - Calibrate all talons				--- yaaa
 - calibrate motors?
 - make the front the wall			--- yaa						
 - if arm complete try to implement	---
 -		test actuator               --- tested actuator not yet mounted
 - implement POV precision movement --- yaaa
 - implement throttle for speed	    --- yeee
 - clean up wiring					---	
 - end of Saturday 					---
 	weigh all the components; 
 	arm, frame, board, etc.

 
*/