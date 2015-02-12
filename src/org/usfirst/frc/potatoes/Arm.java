package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;


public class Arm{
	private Talon bestTalonEber;
    private DigitalInput low, high;
    
	public Arm(int talonPort, int low, int high){
		bestTalonEber = new Talon(talonPort);
		this.low = new DigitalInput(low);
		this.high = new DigitalInput(high);	
	}
	
	public void move(Joystick l, Joystick r){
		move(l.getTrigger(),r.getTrigger());
	}
	
    public void move(boolean up,boolean down){
    	if(up &&  (high.get())){
		   bestTalonEber.set(.7);
		}
		else if(down && low.get()){
		   bestTalonEber.set(-.7);
		}else{
			bestTalonEber.set(0);
		}
    }
}
