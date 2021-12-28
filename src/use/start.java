package use;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
public class start{
 public static void run() throws IOException, FileNotFoundException{
     String configloc=null;
     if(System.getProperty("os.name").contains("Windows")){
       configloc="config\\";
     }
        else{
        configloc="config/";
        }
     Scanner input=new Scanner(System.in);
     if(new File(configloc +".FFUP_Password.txt").exists()){
         share use=new share();
         use.login();
     }
     else if(!new File(configloc +".FFUP_Password.txt").exists()){
         System.out.print("Enter a password for FFUP:");
         String pass=input.nextLine();
         new File("config").mkdir();
         File f=new File(configloc +".FFUP_Password.txt");
         f.createNewFile();
         FileWriter fw=new FileWriter(f);
         share use=new share();
         fw.write(use.sha(pass));
         fw.close();
     }
 }
}