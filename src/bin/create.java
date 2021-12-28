package bin;
import java.io.File;
import java.io.IOException;
public class create{
 public static void run(String s) throws IOException{
   String[] files=s.substring(7,s.length()).split(" ");
    for(String f : files){
     new File(f).createNewFile();
    }
 }
}