package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

public class Arm{
	Joystick leftStick;
	Joystick rightStick;
	private Talon bestTalonEber;
    Encoder stephan;
    DigitalInput low, high;
    
    
	public Arm(int talonPort, int encoderPort1, int encoderPort2, int bumpPort1, int bumpPort2, Joystick stick1, Joystick stick2){
		bestTalonEber = new Talon(talonPort);
		stephan = new Encoder(encoderPort1, encoderPort2, true, EncodingType.k4X);
        leftStick = stick1;
		rightStick = stick2;
		low = new DigitalInput(bumpPort1);
		high = new DigitalInput(bumpPort2);
		
		
	}
    public void Move(){
		double x = 1;
		if(rightStick.getThrottle() > 0){
			x = 1 + rightStick.getThrottle();
		}
		else {
			x = 1 + rightStick.getThrottle();
		}
		x = 1-(x/2);
		if(x<.2) {
			x = .2;
		}

    	if(leftStick.getTrigger() &&  (high.get())){
		   bestTalonEber.set(.7*x);
		}
		else if(rightStick.getTrigger() && low.get()){
		   bestTalonEber.set(-.7*x);
		}else{
			bestTalonEber.set(0);
		}
		//stephan.startLiveWindowMode();
		
  	    System.out.println(x);
  	    System.out.println();
  	   
        
  	    //"Low is " + (low.get()) + "\n"
  	    //"High is " + (high.get()) + "\n"
		//System.out.println(stephan.get());
	}

}
