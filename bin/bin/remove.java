package bin;
import java.io.File;
public class remove{
 private static void recursive(File a){
    for(File f : a.listFiles()){
     if(f.isDirectory()){
        recursive(f);
     }
     f.delete();
    }
    a.delete();
 }

 public static void run(String s){
  File[] arr=new File[s.split(" ").length-1];
  for(int i=0;i<arr.length;i++){
   arr[i]=new File(s.split(" ")[i+1]);
  }
  if(s.split(" ")[1].equals("*")){
    arr=new File(".").listFiles();
    }
    for(File f : arr){
     if(f.isDirectory()){
        recursive(f);
     }
     else if(f.isFile()){
        f.delete();
     }
    }
 }
}