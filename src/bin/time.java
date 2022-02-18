package bin;
import java.util.ArrayList;
import java.lang.Class;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
public class time{
	public static void run(String s){
	 String normalCommand=s.substring(5,s.length());
     try{
	   long earlyTime=System.nanoTime();
       String className = "bin." + normalCommand.split(" ")[0];
       Class<Object> clazz = (Class<Object>) Class.forName(className);
       Object instance = clazz.getConstructor().newInstance();
       clazz.getMethod("run", String.class).invoke(instance, normalCommand);
       System.out.println((System.nanoTime()-earlyTime) + " nanoseconds.");
        } 
      catch (ClassNotFoundException n){
         System.out.println("Command not found.");
       }
       catch (Exception e){		
       }		
	}
}

