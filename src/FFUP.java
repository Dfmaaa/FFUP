import java.awt.Desktop;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.*;
import java.nio.file.*;
import java.util.zip.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.security.MessageDigest;
import java.nio.*;
import java.lang.Class;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
import use.*;
public class FFUP{
    public static void m() throws IOException, ClassNotFoundException,NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
     System.out.println("\r\n"
     +"███████╗███████╗██╗░░░██╗██████╗░\r\n"
     +"██╔════╝██╔════╝██║░░░██║██╔══██╗\r\n"
     +"█████╗░░█████╗░░██║░░░██║██████╔╝\r\n"
     +"██╔══╝░░██╔══╝░░██║░░░██║██╔═══╝░\r\n"
     +"██║░░░░░██║░░░░░╚██████╔╝██║░░░░░\r\n"
     +"╚═╝░░░░░╚═╝░░░░░░╚═════╝░╚═╝░░░░░");
     start s=new start();
     s.run();
     //scanners
     Scanner strin=new Scanner(System.in);
     new File("log").mkdir();
     if(System.getProperty("os.name").contains("Linux")){
         File uptime_time=new File("log/.time_start");
         uptime_time.createNewFile();
         FileWriter wr=new FileWriter(uptime_time);
         wr.write(Long.toString(System.currentTimeMillis()));
         wr.close();
     }
     else{
         File uptime_time=new File("log\\.time_start");
         uptime_time.createNewFile();
         FileWriter wr=new FileWriter(uptime_time);
         wr.write(Long.toString(System.currentTimeMillis()));
         wr.close();
     }
     System.out.println("FFUP 1.0 2021. Type help for a list of commands.");
     while(true){
      String command=strin.nextLine();
      if(command.contains(";")){
        for(String cmd : command.split(";")){
           if(cmd.equals("restart")){
           m();
           }
      else{
        try{
        String className = "bin."+cmd.split(" ")[0];
        Class<Object> clazz = (Class<Object>) Class.forName(className);
        Object instance = clazz.getConstructor().newInstance();
        clazz.getMethod("run",String.class).invoke(instance,cmd);
        }
        catch(ClassNotFoundException n){
            System.out.println("Command not found.");
        }
        catch(Exception e){
        System.out.println("Error, exception: " + e.getClass().getSimpleName());
        e.printStackTrace();
        }
      } 
        }
      }
      else{
      if(command.equals("restart")){
          m();
      }
      else{
        try{
        String className = "bin."+command.split(" ")[0];
        Class<Object> clazz = (Class<Object>) Class.forName(className);
        Object instance = clazz.getConstructor().newInstance();
        clazz.getMethod("run",String.class).invoke(instance,command);
        }
        catch(ClassNotFoundException n){
            System.out.println("Command not found.");
        }
        catch(Exception e){
        }
      }
    }
     }
    }
    public static void main(String[] args) throws IOException,ClassNotFoundException,NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
     m();
    }
}
