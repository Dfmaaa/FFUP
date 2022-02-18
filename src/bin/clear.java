package bin;
public class clear{
 public static void run(String s){
  System.out.print("\033[H\033[2J");
  System.out.flush();
 }
}
