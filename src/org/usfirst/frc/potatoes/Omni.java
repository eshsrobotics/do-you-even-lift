package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Omni {
	private Gyro gyro;
	private SpeedController talonA, talonB,
				            talonC, talonD;
	double k;
	double aFactor = 1.0;
	double bFactor = 1.0; //to make the stronger motor equal in speed to the others
	double cFactor = 1.0;
	double dFactor = .9;
	
	public Omni(int gyroPort, int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort){/**recieves input for the gyro and talon ports**/
		 this(new Gyro(gyroPort), new Talon(frontLeftPort), new Talon(frontRightPort), new Talon(backLeftPort), new Talon(backRightPort));/**this() refers to the object being called**/
	}
	
	public Omni(Gyro gyro, int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort){/**recieves input for the gyro and talon ports**/
		 this(gyro, new Talon(frontLeftPort), new Talon(frontRightPort), new Talon(backLeftPort), new Talon(backRightPort));/**this() refers to the object being called**/
	}
	public Omni(Gyro gyro, SpeedController frontLeft, SpeedController frontRight,
						   SpeedController backLeft,  SpeedController backRight){/**recieves input for the gyro and talon ports**/
		 this.gyro = gyro;
		 talonA = frontLeft;
		 talonB = frontRight;
		 talonC = backLeft;
		 talonD = backRight;
		 this.k = k;
	}
    
	public void drive(double x, double y, double z){
		double radians = Math.toRadians(gyro.getAngle()) - .783;//created new value to get the gyro angle, change to radians, and subtract .783
		double xPrime =  (y*Math.sin(radians)-(x*Math.cos(radians)));
    	double yPrime = -(y*Math.cos(radians)+(x*Math.sin(radians)));
		
		talonA.set((( xPrime) + z)*aFactor);//set() sets pwm values. anywhere between -1 and 1
    	talonD.set(((-xPrime) + z)*dFactor);
    	talonB.set((( yPrime) + z)*bFactor);
    	talonC.set(((-yPrime) + z)*cFactor);
    	System.out.println("angle be" + gyro.getAngle());
		
	}
}
/* TODO:
 - Calibrate all motors				---
 - make the front the wall			---sort of but backwards when rotated 90 degrees
 - clean up wiring					---							
 - if arm complete try to implement	---
 -		test actuator               ---tested actuator not yet mounted
 - implement POV precision movement ---
  - implement throttle for speed	---
 - end of saturday 					---
 	weigh all the components; 
 	arm, frame, board, etc.
 

 
 
*/