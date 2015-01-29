package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Arm{
	Joystick leftStick;
	Joystick rightStick;
	private Talon bestTalonEber;
    
    
	public Arm(int talonPort5, Joystick stick1, Joystick stick2){
		bestTalonEber = new Talon(talonPort5);
		leftStick = stick1;
		rightStick = stick2;
		
	}
    public void Move(){
		if(leftStick.getTrigger()){
		   bestTalonEber.set(.7);
		}
		else if(rightStick.getTrigger()){
		   bestTalonEber.set(-.7);
		}
	}

}
