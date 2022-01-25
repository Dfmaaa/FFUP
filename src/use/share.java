package use;
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
import java.time.LocalTime;
import java.nio.*;
public class share{
    public static void login() throws IOException, FileNotFoundException{
      String configloc=null;
     if(System.getProperty("os.name").contains("Windows")){
       configloc="config\\";
     }
        else{
        configloc="config/";
        }
        String entered_pass=null;
        try (FileReader fileStream = new FileReader(new File(configloc+".FFUP_Password.txt"));
        BufferedReader bufferedReader = new BufferedReader(fileStream)) {
       String line = null;
       while ((line = bufferedReader.readLine())!= null) {
           entered_pass=line;
    }   
    }
    System.out.print("password:");
    String password=new Scanner(System.in).nextLine();
    if(!sha(password).equals(entered_pass)){
        System.out.println("Incorrect password, try again.");
        String logcall=null;
        if(System.getProperty("os.name").contains("Windows")){
            logcall="log\\";
        }
        else{
            logcall="log/";
        }
        if(new File(logcall+".failed_attempts.ffup").exists()==false){
            new File(logcall+".failed_attempts.ffup").createNewFile();
        }
        FileWriter wr=new FileWriter(logcall+".failed_attempts.ffup",true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        wr.write(dtf.format(now)+"\n");
        wr.close();
        boolean correct=false;
        int counter=0;
        while(counter<3){
            System.out.print("password:");
            password=new Scanner(System.in).nextLine();
            if(sha(password).equals(entered_pass)){
                correct=true;
                break;
            }
            else{
                counter++;
                if(counter<3){
                System.out.println("Incorrect password, try again.");
                    logcall=null;
                    if(System.getProperty("os.name").contains("Windows")){
                        logcall="log\\";
                    }
                    else{
                        logcall="log/";
                    }
                    if(new File(logcall+".failed_attempts.ffup").exists()==false){
                        new File(logcall+".failed_attempts.ffup").createNewFile();
                    }
                    FileWriter wrn=new FileWriter(logcall+".failed_attempts.ffup",true);
                    DateTimeFormatter dtfn = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime nown = LocalDateTime.now();
                    wrn.write(dtfn.format(nown)+"\n");
                    wrn.close();
                }
            }
        }
        if(correct==false){
          System.exit(0);
        }
    }
  }
  static String read(String location) throws IOException{
    File f=new File(location);
    String text="";
    try (FileReader fileStream = new FileReader(f);
         BufferedReader bufferedReader = new BufferedReader(fileStream)) {
        String line = null;
        while ((line = bufferedReader.readLine())!= null) {
            text=line;
        }
    }
    return text;
}
    public static String sha(String data){
		try{
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(data.getBytes("UTF-8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) 
				  hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch(Exception ex){
		   throw new RuntimeException(ex);
		}
	}
}