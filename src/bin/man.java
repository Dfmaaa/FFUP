package bin;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
public class man{
 private static String[] read(String location) throws IOException{
  File f=new File(location);
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
     String keyword=s.split(" ")[1];
     String manloc=null;
     if(System.getProperty("os.name").contains("Windows")){
         manloc="man\\";
     }
     else{
         manloc="man/";
     }
   String[] get=read(manloc+keyword+".man");
   for(int x=0;x<get.length;x++){
       System.out.println(get[x]);
   }
 }
}