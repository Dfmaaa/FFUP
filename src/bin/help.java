package bin;
import java.io.IOException;
public class help{
    public static void run(String s) throws IOException{
        String[] a=s.split(" ");
        if(a.length<2){
            System.out.println("Type \"man [command]\" to know more about a command.");
        }
        else{
            man m=new man();
            m.run("man " + a[1]);
        }
    }
}