package org.usfirst.frc.potatoes;

public class PID {
    double k1, k2, k3;
    double sum, previous;
	
    public PID(double k1, double k2, double k3){
		this.k1 = k1;
		this.k2 = k2;
		this.k3 = k3;
	}
    
    public double getValue(double error){
    	sum += error;
    	double out = k1 * sum + k2 (error - previous/*change in height*/) + k3 *error;
    	previous = error;
    	return out;
    	
    }
	
	
}
