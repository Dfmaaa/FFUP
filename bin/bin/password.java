package bin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class password{
 private static String sha(String data){
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
		} 
        catch(Exception ex){
		   throw new RuntimeException(ex);
		}
   }
 private static String read(String location) throws IOException{
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
 private static void change() throws IOException{
 String configloc=null;
    if(System.getProperty("os.name").contains("Windows")){
    configloc="config\\";
    }
    else{
    configloc="config/";
    }
  System.out.print("Enter old password:");
  Scanner pscan=new Scanner(System.in);
  String ask=pscan.nextLine();
  if(sha(ask).equals(read(configloc+".FFUP_Password.txt"))){
      System.out.print("Enter new password:");
      String npass=pscan.nextLine();
      FileWriter wr=new FileWriter(new File(configloc+".FFUP_Password.txt"));
      wr.write(sha(npass));
      wr.close();
  }
  else{
      System.out.println("Invalid password.");
  }
 }
 private static void chek() throws IOException{
    String configloc=null;
    if(System.getProperty("os.name").contains("Windows")){
    configloc="config\\";
    }
    else{
    configloc="config/";
    }
     System.out.print("Enter:");
     Scanner pcheck=new Scanner(System.in);
     String cpass=pcheck.nextLine();
     if(sha(cpass).equals(read(configloc+".FFUP_Password.txt"))){
         System.out.println("Yes, that is the correct password.");
     }
     else{
         new File("log").mkdir();
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
     }
 }
 public static void run(String s) throws IOException{
  boolean chang=false;
  boolean check=false;
  String[] m=s.split(" ");
  for(int x=1;x<m.length;x++){
      if(m[x].equals("change")){
          chang=true;
      }
      else if(m[x].equals("check")){
          check=true;
      }
  }
  if(chang){
      change();
  }
  else if(check){
   chek();
  }
  else{
      System.out.println("Invalid option.");
  }
 }
}