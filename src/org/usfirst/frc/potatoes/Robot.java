package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {
    Joystick leftStick;
    CameraServer server;
    Omni omni;
    Arm arm;
    double theta;
    Gyro gyro;
    double sensitivity;
    final static double deadBand = 0.4;
    final static double maxError = 0.3 ;
    
    public void robotInit() {
    	//enable the camera
    	server = CameraServer.getInstance();
        server.setQuality(100);
        server.startAutomaticCapture("cam0");
        
        gyro = new Gyro(0);
        //gyro, fl, fr, bl, br
    	omni = new Omni(gyro, 0, 1, 2, 3);
    	
    	//motor, 
    	arm = new Arm(4,0,1);
    	
    	//joystick
        leftStick = new Joystick(0);
    }
    
    public void autonomousPeriodic() {
    }
     
    public void teleopInit() {
    }
     
    public void teleopPeriodic() {
    	
    	if (leftStick.getZ() < -deadBand) {
    		theta += (leftStick.getZ() + deadBand)*5;
    	}
    	else if (leftStick.getZ() > deadBand) {
    		theta += (leftStick.getZ()- deadBand)*5;
    	}
    	double error = gyro.getAngle()-theta;
    	error /= 100;
    	// clamping the value of error. so we donat spin uncontrollably
    	if (error < -maxError) {
    		error = -maxError;
    	}
    	else if(error > maxError) {
    		error = maxError;
    	}
    	
    	
    	omni.drive(leftStick.getX(), leftStick.getY(), error);

    	System.out.println(error);
    }
     
    public void testPeriodic() {	    
    }
}

