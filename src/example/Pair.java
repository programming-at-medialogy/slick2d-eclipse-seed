package example;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * The {@code Pair} class acquires both {@code Race} and {@code Ability} objects. 
 *
 */
public class Pair {
	
	

	
	 	Pair[] pair = new Pair[2];
	 	
	 	int units;

	    Race race; 
	    Ability ability;
	    boolean active; //if active the abilities apply and the player can control the race
	    
		public Pair(Race race, Ability ability) {
			this.race = race;
			this.ability = ability;
			units = race.unitAmount + ability.unitAmount;
		}
		
		
		public void set(Race race, Ability ability){
		      pair[0] = new Pair(race, ability); 
			} 
		
		
	    public boolean setActive(boolean a) {
	        if (a == true) 
	            return active = true;
	        else
	            return active = false;
	    } 
	    
	    public void effect(){
	    	Class<?> noparams[] = {};
	    	
	    	Class[] paramInteger = new Class[1];
			paramInteger[0] = Integer.class;
			
	    	Method method = null;
			try {
				method = race.type.getClass().getMethod(race.effect, paramInteger);
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
			
	    	try {
	    		Class<?> cls = Class.forName("com.example.Bonus");
				Object obj = cls.newInstance();
	    		
				method.invoke(obj, race.amount);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

}