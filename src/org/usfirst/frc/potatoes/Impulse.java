package org.usfirst.frc.potatoes;

/**
 * 
 * @author pltwe5
 *
 */
public class Impulse extends Debounce{
	boolean isPressed = false;
	Omni omni;
	int direction;
	
	/**
	 * Constructor that passes in omni and direction
	 * @param omni
	 * @param direction
	 */
	public Impulse(Omni omni,int direction){
	  this.omni = omni;
	  this.direction = direction;
	}
    
   /**
    * Method that does absolutamente nada.
    */
	public void onUp(){
    }
    
	/**
	 * Method that causes robot to turn.
	 */
    public void onDown(){
    	omni.drive(0, 0, (direction));
    }
}
