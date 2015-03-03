package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon;//changed for the CANTalons

/**
 * 
 * @author pltwe5
 *
 */
public class Arm{
	private CANTalon bestTalonEber;
    private DigitalInput low, high;
    
	/**
	 * Constructor for arm that sets things equal to things.
	 * @param talonPort
	 * @param low
	 * @param high
	 */
    public Arm(int talonPort, int low, int high){
		bestTalonEber = new CANTalon(talonPort);
		this.low = new DigitalInput(low);
		this.high = new DigitalInput(high);	
	}
	
	/**
	 * Runs move method on joystick passed in when buttons four or six are pushed.
	 * @param l
	 */
    public void move(Joystick l){
		move(l.getRawButton(4),l.getRawButton(6)); // for buttons number six and 4 on the top of the joystick
	}
	
    /**
     * Takes up and down booleans to stop movement when arm reaches high limit switch or low limit switch.
     * @param up
     * @param down
     */
    public void move(boolean up,boolean down){
    	if(up && !high.get()){
		   bestTalonEber.set(.7);
		}
		else if(down && !low.get()){
		   bestTalonEber.set(-.7);
		}else{
			bestTalonEber.set(0);
		}
    }
}
