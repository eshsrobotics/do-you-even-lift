package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class Robot extends IterativeRobot {
    Joystick leftStick;
    CameraServer server;
    Omni omni;
    Arm arm;
    double theta;
    Gyro gyro;
    double sensitivity;
    final static double deadBand = 0.1, maxError = 0.3 ;
    boolean leftDown = false;
    boolean rightDown = false;
    Debounce turn;
    Calibration calibration;
    PID turning;
    
    public void robotInit() {
    	//enable the camera
    	server = CameraServer.getInstance();
        server.setQuality(100);
        server.startAutomaticCapture("cam0");
        
        gyro = new Gyro(0);
        leftStick = new Joystick(0);
        //gyro, fl, fr, bl, br
    	omni = new Omni(gyro, 0, 1, 2, 3, leftStick);
    	
    	//motor, 
    	arm = new Arm(1,1,0);
    	
    	//joystick
        calibration = new Calibration(gyro);
        
       turning = new PID(.003,.5,.2);
       
    }
    
    public void autonomousPeriodic() {
    }
     
    public void teleopInit() {
    	gyro.reset();//resets the Gyro object  gyro whenever teleop is initialized
    	theta = 0; //Want theta
    	calibration.reset();
    	
    }


    
    public void teleopPeriodic() {
    	
    	//turn.press(leftStick.getRawButton(11));
    	
    	calibration.press(leftStick.getTrigger());
    	double error = calibration.getError(); //due to drift
    	

    	//System.out.println(gyro.getAngle()-error);
    	
    	if (leftStick.getZ() < -deadBand) {
    		theta += (leftStick.getZ() + deadBand);
    	}
    	else if (leftStick.getZ() > deadBand) {
    		theta += (leftStick.getZ()- deadBand);
    	}
    	error = gyro.getAngle()-error-theta;
    	// Previously we had "error /= 10" here.  Know what happened?  Everyone screamed and hid their children.  err ma gerrd
    	error /= 10;
    	
    	// clamping the value of error. so we don't spin uncontrollably
    	/*if (error < -maxError) {
    		error = -maxError;
    	}
    	else if(error > maxError) {
    		error = maxError;
    	} */
    	//flipped x direction to negative to ensure that robot is moving in correlation with joystick
    	
    	arm.move(leftStick);
    	error = turning.getValue(error);
    	omni.drive(-leftStick.getY(), leftStick.getX(),error);
    	
    	
        if(leftStick.getPOV() == 0) {
    		omni.drive(1,0, error);
    	}
    	else if (leftStick.getPOV() == 90 ){
    		omni.drive(0, 1, error);
    	}
    	
    	else if (leftStick.getPOV() == 180){
    		omni.drive(-1, 0, error);
    	}
    	
    	else if (leftStick.getPOV() == 270){
    		omni.drive(0, -1, error);
    	}
        
        /*
    	if(leftStick.getRawButton(8)){
    		if(theta%360 > 180.0)
    			theta += theta%360-180;
    		else
    			theta -= theta%360;
    	}
        */

    }
    
    public void testPeriodic() {	   
    	
    }
}

        
/* code for talon callibration (button 11 = max, button 12 = min)
    if(leftStick.getRawButton(11))
       omni.drive(1, 0 ,0);
   
    else if (leftStick.getRawButton(12))
	   omni.drive(-1,0,0);
	   */

/*System.out.println("Y Stick is " + leftStick.getY() + "\n");
System.out.println("X Stick is " + leftStick.getX());
*/
    	
    	
  
     

