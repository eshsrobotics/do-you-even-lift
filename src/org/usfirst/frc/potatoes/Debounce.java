package org.usfirst.frc.potatoes;

/**
 *
 */
public abstract class Debounce{
	protected boolean isPressed = false;
	public void press(boolean down){
		if(isPressed && !down){
			onUp();
			isPressed = false;
		}else if(!isPressed && down){
			onDown();
			isPressed = true;
		}
	}
	protected abstract void onUp();
	protected abstract void onDown();
}