package org.usfirst.frc.potatoes;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;

public class Omni {
	private Gyro gyro;/**creates gyro**/
	private Talon talonA, talonB,/**creates talons**/
				  talonC, talonD;
	double k = .7;/**the constant**/
	
	public Omni(int gyroPort, int talonPort1, int talonPort2, int talonPort3, int talonPort4){/**recieves input for the gyro and talon ports**/
		 this(gyroPort, talonPort1, talonPort2, talonPort3, talonPort4, .7);/**this() refers to the object being called**/
	}
	public Omni(int gyroPort, int talonPort1, int talonPort2, int talonPort3, int talonPort4, double k){/**recieves input for the gyro and talon ports**/
		 this(new Gyro(gyroPort), talonPort1, talonPort2, talonPort3, talonPort4, k);/**this() refers to the object being called**/
	}
	public Omni(Gyro gyro, int talonPort1, int talonPort2, int talonPort3, int talonPort4, double k){/**recieves input for the gyro and talon ports**/
		 this(gyro, new Talon(talonPort1), new Talon(talonPort2), new Talon(talonPort3), new Talon(talonPort4), k);/**this() refers to the object being called**/
	}
	public Omni(Gyro gyro, Talon talon1, Talon talon2, Talon talon3, Talon talon4, double k){/**recieves input for the gyro and talon ports**/
		 this.gyro = gyro;
		 talonA = talon1;
		 talonB = talon2;
		 talonC = talon3;
		 talonD = talon4;
		 this.k = k;
	}
    
	public void Drive(double x, double y, double z){
    	double radians = Math.toRadians(gyro.getAngle());
    	double f = ((k*y)*Math.cos((Math.PI/4)-radians)-(k*x)*Math.sin((Math.PI/4)-radians))/Math.cos(2*radians); //f is the magnitude of talonB + talonC
    	double e = ((k*y)*Math.sin((Math.PI/4)-radians)+(k*x)*Math.cos((Math.PI/4)-radians))/-Math.cos(2*radians); //e is the magnitude of talonA + talonD
    	talonA.set((e/2) + z);/**set() sets pwm values. anywhere between -1 and 1 **/
    	talonD.set((e/2) - z);
    	talonB.set((f/2) + z);
    	talonC.set((f/2) - z);
	}
}
