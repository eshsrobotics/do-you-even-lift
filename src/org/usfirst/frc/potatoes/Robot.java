package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * 
 * @author pltwe5
 *
 */
public class Robot extends IterativeRobot {
    Joystick leftStick;
    CameraServer server;
    Omni omni;
    Impulse impulse;
    Impulse negImpulse;
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
    CommandGroup autoCom;
    
    
    public void robotInit() {
    	//enable the camera
    	server = CameraServer.getInstance();
        server.setQuality(100);
        server.startAutomaticCapture("cam0");
         
        gyro = new Gyro(0);
        leftStick = new Joystick(0);
        
        //fl, fr, bl, br
    	omni = new Omni(0, 1, 2, 3);
    	
    	//omni, direction
    	impulse = new Impulse(omni, 1);
    	negImpulse = new Impulse(omni, -1);
    	

    	//motor, digital input, digital input
    	arm = new Arm(1,1,0);
    	
    	//gyro
        calibration = new Calibration(gyro);
        turning = new PID(.003,.5,.2);
        autoCom = new AutoCom(omni, arm);
    }
    
    public void autonomousInit(){
    	autoCom.start();
    }
    
    public void autonomousPeriodic() {
      Scheduler.getInstance().run();    	
    }
     
    public void teleopInit() {
    	gyro.reset(); //resets the Gyro object  gyro whenever teleop is initialized
    	theta = 0;    //reset theta
    	calibration.reset();
    }

    public void teleopPeriodic() {
    	calibration.press(leftStick.getTrigger());
    	double error = calibration.getError(); //due to drift
    	

    	if (leftStick.getZ() < -deadBand) {
    		theta += (leftStick.getZ() + deadBand);
    	}
    	else if (leftStick.getZ() > deadBand) {
    		theta += (leftStick.getZ()- deadBand);
    	}
    	error = gyro.getAngle()-error-theta;
    	System.out.println(error);
    	// Previously we had "error /= 10" here.  Know what happened?  Everyone screamed and hid their children.  err ma gerrd
    	error /= 10;
    	error = turning.getValue(error);
    	
    	arm.move(leftStick);
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
        
        impulse.press(leftStick.getRawButton(8));
        negImpulse.press(leftStick.getRawButton(7));
    }
    
    public void testPeriodic() {	    	
    }
    
    
}

        

    	
  
     

