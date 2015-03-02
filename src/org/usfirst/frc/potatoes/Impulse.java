package org.usfirst.frc.potatoes;

public class Impulse extends Debounce{
	boolean isPressed = false;
	Omni omni;
	int direction;
	
	public Impulse(Omni omni,int direction){
	  this.omni = omni;
	  this.direction = direction;
	}
    
    public void onUp(){
  
    }
    
    public void onDown(){
    	omni.drive(0, 0, (direction));
    	
    }
}
