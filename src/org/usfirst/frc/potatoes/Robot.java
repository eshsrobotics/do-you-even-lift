package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;




/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;  // class that handles basic drive operations
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    CameraServer server;
  
    
    
    private Gyro gyro;
    Omni myOmniDrive;
    Arm myArm;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//myOmniDrive = new Omni(1, 0, 0, 0, 0);
    	
    	server = CameraServer.getInstance();
         server.setQuality(50);
         //the camera name (ex "cam0") can be found through the roborio web interface
         server.startAutomaticCapture("cam0");
         myRobot = new RobotDrive(0, 1);
         myRobot.setExpiration(0.1);
         leftStick = new Joystick(0);
         rightStick = new Joystick(1);
         gyro = new Gyro(1);             // Gyro on Analog Channel 1
         // joysticks must be initialized before Arm()
         myArm = new Arm(2, leftStick, rightStick);
       
    }

    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if(Timer.getMatchTime()<1){
    		myRobot.drive(-0.25, 0);
    	}
    	else{
    		myRobot.drive(0, 0);
    	}
    }

    
    /**
     * This function is called periodically during operator control
     */
    
    public void teleopInit() {
    	gyro.reset();//resets the gyro
    }
    
    public void teleopPeriodic() {
    	boolean tooBool4Skewl = true;
    	if(tooBool4Skewl == true){
    	  myRobot.setSafetyEnabled(true);
    	  myRobot.tankDrive(leftStick, rightStick);
    	  
    	  myArm.Move();
    	  
    	  //double degrees = gyro.getAngle();
    	 // double radians = Math.toRadians(degrees);
    	 
    	  
    	  //double z = Math.atan2(leftStick.getY(),leftStick.getX())-radians+3.14/2;
    	 // double length = leftStick.getY()*leftStick.getY()+leftStick.getX()*leftStick.getX();
    	
    	  //if(leftStick.getTrigger()){
    		// myRobot.tankDrive(- z*length,z*length);
    	  //}else{
    		 myRobot.tankDrive(leftStick.getY() - leftStick.getZ()*.7, leftStick.getY() + leftStick.getZ()*.7);
    	  //}
        }
    	else{
    	  //myOmniDrive.Drive(leftStick.getX(),leftStick.getY(),leftStick.getZ());
    	  
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
