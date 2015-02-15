package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Omni {
	private Gyro gyro;
	private SpeedController talonA, talonB,
				            talonC, talonD;
	private Joystick leftStick;
	
	double k;
	double aFactor = 1.0;
	double bFactor = 1.0; 
	double cFactor = 1.0;
	double dFactor = 1.0;
	
	public Omni(int gyroPort, int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort, int joyStickPort){/**recieves input for the gyro and talon ports**/
		 this(new Gyro(gyroPort), new Talon(frontLeftPort), new Talon(frontRightPort), new Talon(backLeftPort), new Talon(backRightPort), new Joystick(joyStickPort));/**this() refers to the object being called**/
	}
	
	public Omni(Gyro gyro, int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort, Joystick leftStick){/**recieves input for the gyro and talon ports**/
		 this(gyro, new Talon(frontLeftPort), new Talon(frontRightPort), new Talon(backLeftPort), new Talon(backRightPort), leftStick);/**this() refers to the object being called**/
	}
	public Omni(Gyro gyro, SpeedController frontLeft, SpeedController frontRight,
						   SpeedController backLeft,  SpeedController backRight, Joystick leftStick){/**recieves input for the gyro and talon ports**/
		 this.gyro = gyro;
		 this.leftStick = leftStick;
		 talonA = frontLeft;
		 talonB = frontRight;
		 talonC = backLeft;
		 talonD = backRight;
		 this.k = k;
		 
		 
	}
    
	public void drive(double x, double y, double z){
		
		
		double radians = Math.toRadians(0 - gyro.getAngle()) - .783;//created new value to get the gyro angle, change to radians, and subtract .783
		double xPrime =  (y*Math.sin(radians)-(x*Math.cos(radians)));
    	double yPrime = -(y*Math.cos(radians)+(x*Math.sin(radians)));
		
      if(leftStick.getThrottle()>.5){
  		  aFactor = 0.5;
  		  bFactor = 0.5;
  		  cFactor = 0.5;
  		  dFactor = 0.5;
  	  }
  	  else if(leftStick.getThrottle()>0.0){
  		aFactor = 0.7;
		bFactor = 0.7;
		cFactor = 0.7;
		dFactor = 0.7;
  	  }
  	  else if(leftStick.getThrottle()>-0.5){
  		aFactor = 0.8;
		bFactor = 0.8;
		cFactor = 0.8;
		dFactor = 0.8;
  	  }
  	  else if(leftStick.getThrottle()>=-1){
  		aFactor = 0.9;
		bFactor = 0.9;
		cFactor = 0.9;
		dFactor = 0.9;
  	  }
    	
		talonA.set((( xPrime) + z)*aFactor);//set() sets pwm values. anywhere between -1 and 1
    	talonD.set(((-xPrime) + z)*dFactor);
    	talonB.set((( yPrime) + z)*bFactor);
    	talonC.set(((-yPrime) + z)*cFactor);
    	
    
    	
    	//System.out.println("angle be" + gyro.getAngle());
    	
    	
    	/* code for talon callibration
    	talonA.set(x);
    	talonD.set(x);
    	talonB.set(x);
    	talonC.set(x); 
    	*/
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