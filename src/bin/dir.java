package bin;
import java.io.File;
public class dir {
    private static void print(File a){
        try {
            for (File f : a.listFiles()) {
                System.out.println(f.getName());
            }
        }
        catch(Exception e){
            System.out.println("Could not show files in " + a.getName());
        }
    }
    private static boolean contains(String[] a, String value){
        for(int x=0;x<a.length;x++){
            if(a[x].equals(value)){
                return true;
            }
        }
        return false;
    }
    private static void recursive(File a){
        for(File f : a.listFiles()){
            try {
                if (f.isDirectory()) {
                    System.out.println(f.getAbsolutePath());
                    recursive(f);
                } else {
                    System.out.println(f.getAbsolutePath());
                }
            }
            catch(Exception e) {
                System.out.println("Could not show files in " + f.getAbsolutePath());
            }
        }
    }
    public static void run(String s){
        boolean recursive_bool=contains(s.split(" "), "--recursive");
        if(recursive_bool) {
            if (s.split(" ").length == 2) {
                recursive(new File("."));
            } else {
                String[] files = s.substring(4, s.length()).replace("--recursive", "").split(" ");
                for (String file : files) {
                    recursive(new File(file));
                }
            }
        }
            else{
              if(s.split(" ").length==1){
                  print(new File("."));
              }
              else{
                  String[] files=s.substring(4,s.length()).split(" ");
                  for( String file : files){
                      print(new File(file));
                  }
              }
            }
        }
    }