package bin;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
public class show{
    private static String[] altarr(ArrayList<String> a){
        String[] ret=new String[a.size()];
        for(int x=0;x<ret.length;x++){
            ret[x]=a.get(x);
        }
        return ret;
    }
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
    public static void run(String s){
        boolean reverse=false;
        if(Arrays.toString(s.split(" ")).contains("-r")){
            reverse=true;
        }
        String[] files=null;
        if(reverse){
            files=s.split("-r")[1].split(" ");
        }
        else{
            files=s.substring(5,s.length()).split(" ");
        }
        if(reverse){
         for(int x=0;x<files.length;x++){
            String[] get={""};
            try{
                get=read(files[x]);
            }
            catch(IOException e){
                System.out.println("File not found");
            }
            for(int y=get.length-1;y>=0;y--){
                System.out.println(get[y]);
            }
         }
        }
        else{
            for(int x=0;x<files.length;x++){
                String[] get={""};
                try{
                    File f=new File(files[x]);
                    try (FileReader fileStream = new FileReader(f);
                    BufferedReader bufferedReader = new BufferedReader(fileStream)) {
                    String line = null;
                    while ((line = bufferedReader.readLine())!= null) {
                     System.out.println(line);
                     }
                  }
                }
                catch(IOException e){
                    System.out.println("File not found.");
                }
                
            }
        }
    }
}
