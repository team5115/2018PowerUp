package com.team5115.robot;

import com.team5115.Constantos;

import edu.wpi.first.wpilibj.Joystick;

public class InputManager {

    /**
     * InputManager is the place where inputs from the joystick go to die
     * Here, they are converted into methods for the rest of the code
     * In order to reference a joystick input, you must reference the InputManager class
     * If you don't really understand Getters and Setters you should go to Stack overflow or try (through trial and error) making them in a new project to see how they work
     */

    static Joystick joy = new Joystick(0);

    //The following methods deal with the basic driving functionalities
    public static double getForward() {
    	System.out.println("Y-Axis " + -treatAxis(joy.getRawAxis(Constantos.AXIS_Y)));
        return -treatAxis(joy.getRawAxis(Constantos.AXIS_Y));
    }

    public static double getTurn() {
    	//System.out.println("X-Axis " +joy.getRawAxis(Constants.AXIS_X));
        return -treatAxis(joy.getRawAxis(Constantos.AXIS_X));
    }

    //These methods are controlled by the nub on the top of the joystick
    public static double getHat() {
        return joy.getPOV();
    }

    public static double getThrottle() {
        // Joystick give 1 to -1 but we need 0 to 1
    	//System.out.println("throttle " + ((1 - joy.getThrottle()) / 2));
        return (1 - joy.getThrottle()) / 2;
    }

    // Handles expo and deadband
    public static double treatAxis(double val) {
        if (Math.abs(val) < Constantos.JOYSTICK_DEADBAND) {
            val = 0;
        }
        else {
        	 double sign = (Math.signum(val));
             val = Math.pow(Math.abs(val), 2 * getThrottle() + 1);
        
             if(sign != Math.signum(val)){
            	 val *= sign;
             }
        }
        
        System.out.println("throttle " + getThrottle());
        System.out.println("value " + val);

        
        return val;
    }
    public static boolean kill(){
    	return joy.getRawButton(Constantos.KILL);
    }
    public static boolean switchHeight(){
    	return joy.getRawButton(Constantos.SWITCH);
    }
    public static boolean scaleHeight(){
    	return joy.getRawButton(Constantos.SCALE);
    }
    public static boolean returnHeight(){
    	return joy.getRawButton(Constantos.RETURN);
    }
    public static boolean intake(){
    	return joy.getRawButton(Constantos.INTAKE);
    }
    public static boolean eject(){
    	return joy.getRawButton(Constantos.EJECT);
    }
    public static boolean moveUp(){
    	return joy.getRawButton(Constantos.UP);
    }
    public static boolean moveDown(){
    	return joy.getRawButton(Constantos.DOWN);
    }
}
