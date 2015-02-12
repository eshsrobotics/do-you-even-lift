package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {
    Joystick leftStick;
    CameraServer server;
    Omni omni;
    Arm arm;
    
    public void robotInit() {
    	//enable the camera
    	server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
        
        //gyro, fl, fr, bl, br
    	omni = new Omni(0, 0, 1, 2, 3);
    	
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
    	omni.drive(leftStick.getX(), leftStick.getY(), leftStick.getZ());
    }
     
    public void testPeriodic() {	    
    }
}

