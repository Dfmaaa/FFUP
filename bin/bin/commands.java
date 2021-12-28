package bin;
import java.io.File;
public class commands{
     public static void run(String s){
     for( File f : new File("bin").listFiles()){
         System.out.println(f.getName().replaceFirst("[.][^.]+$", ""));
     }
    }
}