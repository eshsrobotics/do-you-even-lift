package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon;//changed for the CANTalons


public class Arm{
	private CANTalon bestTalonEber;
    private DigitalInput low, high;
    
	public Arm(int talonPort, int low, int high){
		bestTalonEber = new CANTalon(talonPort);
		this.low = new DigitalInput(low);
		this.high = new DigitalInput(high);	
	}
	
	public void move(Joystick l){
		move(l.getRawButton(4),l.getRawButton(6));// for buttons number six and 4 on the top of the joystick
	}
	
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
