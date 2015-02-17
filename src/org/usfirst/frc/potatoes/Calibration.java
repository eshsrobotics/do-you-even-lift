package org.usfirst.frc.potatoes;
import edu.wpi.first.wpilibj.Gyro;

public class Calibration extends Debounce {
	Gyro gyro;
	double error, tmp, time, rate;
	public Calibration (Gyro gyro){
		this.gyro = gyro;
	}
	@Override
	protected void onUp() {
		double temp = gyro.getAngle()-tmp;
		rate = temp/time;
		error += temp;

	}

	@Override
	protected void onDown() {
		tmp = gyro.getAngle();
	}
	
	public double getError(){
		if(isPressed){
			time++;
		}else{
			error += rate;
		}
		return error;
	}
	
	public void reset(){
		error = 0;
	}
}
