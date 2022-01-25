package bin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class uptime {
    private static String[] read(File f) throws IOException {
        ArrayList<String> a=new ArrayList<String>();
        try (FileReader fileStream = new FileReader(f);
             BufferedReader bufferedReader = new BufferedReader(fileStream)) {
            String line = null;
            while ((line = bufferedReader.readLine())!= null) {
                a.add(line);
            }
        }
        return altarr(a);
    }
    private static String[] altarr(ArrayList<String> a){
        String[] ret=new String[a.size()];
        for(int x=0;x<ret.length;x++){
            ret[x]=a.get(x);
        }
        return ret;
    }
    public static void run(String s) throws IOException{
      if(System.getProperty("os.name").contains("Linux")){
          System.out.println(System.currentTimeMillis()-Long.valueOf(read(new File("log/.time_start"))[0]) + " milliseconds.");
      }
      else{
          System.out.println(System.currentTimeMillis()-Long.valueOf(read(new File("lo\\.time_start"))[0]) + " milliseconds.");
      }
    }
}
