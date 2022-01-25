package bin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class blank {
    private static ArrayList<File> containFiles=new ArrayList<File>();
    private static void makeBlank(File a) throws IOException{
        FileWriter wr=new FileWriter(a);
        wr.write("");
        wr.close();
    }
    private static void makeBlank(File[] a) throws IOException{
        for(int x=0;x<a.length;x++){
            makeBlank(a[x]);
        }
    }
    private static void getfiles(File a){
      for(File n : a.listFiles()) {
          if (n.isDirectory()) {
              getfiles(n);
          }
          if (n.isFile()) {
              containFiles.add(n);
          }
      }
    }
    private static File[] get(File a){
        getfiles(a);
        ArrayList<File> conv_this=containFiles;
        containFiles=new ArrayList<File>();
        return conv(conv_this);
    }
    private static File[] conv(ArrayList<File> a){
        File[] send=new File[a.size()];
        for(int x=0;x<send.length;x++){
            send[x]=a.get(x);
        }
        return send;
    }
    public static void run(String s) throws IOException{
     String[] locs=s.substring(6,s.length()).split(" ");
     for(int x=0;x<locs.length;x++){
         File f=new File(locs[x]);
         if(f.exists()==false){
             System.out.println(locs[x] + " doesn't exist.");
         }
        else if(f.isDirectory()){
             makeBlank(get(f));
         }
         else{
           makeBlank(f);
         }
     }
    }
}