package bin;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class editor{
	//variables
	private static long lines=0L;
	private static ArrayList<String> content=new ArrayList<String>();
    //methods
	private static void append(File a, String data) throws IOException{
     FileWriter wr=new FileWriter(a,true);
     wr.write(data+"\n");
     wr.close();
	}
	private static void clear(){
	 System.out.print("\033[H\033[2J");
     System.out.flush();
	}
	private static ArrayList<String> read(File a) throws IOException{
        ArrayList<String> n=new ArrayList<String>();
        try (FileReader fileStream = new FileReader(a);
        BufferedReader bufferedReader = new BufferedReader(fileStream)) {
        String line = null;
        while ((line = bufferedReader.readLine())!= null) {
              n.add(line);
              content.add(line);
              lines++;
           }
         }
         return n;
        }
    private static void printArrayList(ArrayList<String> a){
	 for(int x=0;x<a.size();x++){
		System.out.println((x+1) + ". "+ a.get(x));
	 }
	}
	private static void empty(File a) throws IOException{
	 FileWriter wr=new FileWriter(a);
	 wr.write("");
	 wr.close();
    }	
	private static void writeArrayList(File a, ArrayList<String> n) throws IOException{
	 empty(a);
	 FileWriter wr=new FileWriter(a,true);
	 for(int x=0;x<n.size();x++){
		wr.write(n.get(x)+"\n");
	 }
	 wr.close();
	}
	public static void run(String s)throws IOException{
     clear();
     //variables
     boolean newFile=s.split(" ").length==1;
     File mainf=null;
     Scanner mainScanner=new Scanner(System.in);
     Long currentLine=0L;
     boolean change=false;
     //end variables
     if(newFile){
	  System.out.println("                               NEW FILE                             ");
	  System.out.println("                                                                    ");
	 }
	 else{
	  mainf=new File(s.split(" ")[1]);
	  if(mainf.exists()==false||mainf.isDirectory()==true){
		mainf.createNewFile();
	  }
	  ArrayList<String> red=read(mainf);
	  System.out.println("Show contents of file?(y/n)");
	  boolean choice=mainScanner.nextLine().toLowerCase().equals("y");
	  if(choice){
		clear();
		printArrayList(red);
	  }
	  else{
	   clear();
	  }
	 }
	 //main loop
	 while(true){
		System.out.print("Action: ");
		String action=mainScanner.nextLine();
		if(action.split(" ")[0].equals("selectline")){
		 currentLine=Long.valueOf(action.split(" ")[1])-1;
		 System.out.println("SELECTED LINE: " + (currentLine+1));
		}
		if(action.equals("save")){
		 if(change){
		 if(newFile){
		  System.out.print("Name: ");
		  String name=mainScanner.nextLine();
		  File n=new File(name);
		  if(n.exists()==true&n.isFile()){
			  System.out.println("File already exists. Overwrite?(y/n)");
			  boolean ov=mainScanner.nextLine().toLowerCase().equals("y");
			  if(ov){
				  writeArrayList(n,content);
				  change=false;
				  System.out.println("Saved!");
			  }
			  else{
			   System.out.println("Type save again and choose a different name!");
		  }
		 }
	 }
	 else{
		writeArrayList(mainf,content);
		change=false;
		System.out.println("Saved!");
	 }
 }
	 else{
	  System.out.println("Nothing to change!");
	 }
	 }
	 if(action.split(" ")[0].equals("append")){
		 content.add(action.substring(7,action.length()));
		 System.out.println("Appended. Use \"save\" to apply changes");
		 change=true;
	 }
	 if(action.split(" ")[0].equals("getline")){
	  System.out.println(content.get(Integer.parseInt(action.split(" ")[1])-1));
	 }
	 if(action.split(" ")[0].equals("write")){
	  content.set(currentLine.intValue(),action.substring(6,action.length()));
	  System.out.println("Changed line " + (currentLine+1));
	  change=true;
	 }
	 if(action.equals("show")){
	   printArrayList(content);
	 }
	 if(action.equals("numlines")){
	  System.out.println("Number of lines: " + content.size());
	 }
	 
	}
 }
}
