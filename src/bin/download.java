package bin;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
public class download {
    private static String def(String url){
        return url.split("/")[url.split("/").length-1];
    }
    public static void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
    }
    public static void run(String s) throws Exception {
     String[] urls=s.substring(9,s.length()).split(",");
      int full=urls.length;
      int counter=0;
      System.out.println(full + " URLS found.");
      int successCounter=0;
     for(int x=0;x<urls.length;x++){
       System.out.println("--------------------------------");
       try {
           if(urls[x].split(" ").length==2) {
               System.out.println("URL: " + urls[x].split(" ")[0] + " , filename: " + urls[x].split(" ")[1]);
               downloadFile(new URL(urls[x].split(" ")[0]), urls[x].split(" ")[1]);
           }
           else{
             String defName=def(urls[x].split(" ")[0]);
             System.out.println("No filename input found for " + urls[x].split(" ")[0] + " , default name: " + defName);
               System.out.println("URL: " + urls[x].split(" ")[0] + " , filename: " + defName);
               downloadFile(new URL(urls[x].split(" ")[0]), defName);
           }
           counter++;
           System.out.println("Finished " + counter + "/" + full + "(" + (double)(counter*100)/full + ")");
           successCounter++;
       }
       catch(Exception e){
           System.out.println("Error encountered, exception: " + e.getClass().getSimpleName());
       }
     }
     System.out.println("Complete. " + successCounter + " files installed successfully.");
    }
}
