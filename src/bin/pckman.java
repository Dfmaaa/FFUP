package bin;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class pckman{
	private static String sourceFileLocation=" "; //users can change this.
	private static String packageListFile=" "; //users can change this.
    private static ArrayList<String> sourcesToArrayList(File sources) throws IOException{
		FileReader read=new FileReader(sources);
		BufferedReader bread=new BufferedReader(read);
		ArrayList<String> realSources=new ArrayList<String>();
		String containLine="";
		while((containLine=bread.readLine())!=null){
			if(containLine.charAt(0)!='#'){
				realSources.add(containLine);
			}
		}
		return realSources;
	}
	public static boolean getBooleanExistence(URL theurl) throws IOException{
			HttpURLConnection getResponse=(HttpURLConnection)theurl.openConnection();
			try{
				assert HttpURLConnection.HTTP_OK==getResponse.getResponseCode();
				return true;
			}
			catch(Exception e){
				return false;
			}
	}
	public static void downloadFile(URL url, String fileName) throws Exception{
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
    }
    public static boolean exists(String[] arr, String value){
		int start=0;
		int end=arr.length-1;
		while(end!=-1){
			if(arr[start].equals(value)){
				return true;
			}
			if(arr[end].equals(value)){
				return true;
			}
			end--;
			start++;
		}
		return false;
	}
	public static void installPackages(String url) throws IOException{
			try{
				String fname=url.split("/")[url.split("/").length-1];
				downloadFile(new URL(url),"bin/"+fname);
				File defaultPackageListFile=new File("bin/packages.ls");
				if(packageListFile.equals(" ")){
					if(defaultPackageListFile.exists()==false){
					defaultPackageListFile.createNewFile();
				    }
					packageListFile="bin/packages.ls";
				}
				defaultPackageListFile=new File(packageListFile);
				if(defaultPackageListFile.exists()==false){
					defaultPackageListFile.createNewFile(); //if user changed the code to point to their custom package list, but it doesn't exist
				}
				System.out.println("Adding package to package list("+defaultPackageListFile.getAbsolutePath()+")...");
				FileWriter ap=new FileWriter(defaultPackageListFile,true);
				ap.write(fname+"\n");
				ap.close();
				System.out.println("Done.");
			}
			catch(Exception e){
				System.out.println("Installation failed.");
			}
		}
	private static void makeBlank(File a) throws IOException{
		FileWriter erase=new FileWriter(a);
		erase.write("");
		erase.close();
	}
	public static void uninstallPackages(String pkID) throws IOException{
		System.out.println("Uninstalling...");
		File pkgToDelete=new File("bin/"+pkID);
		if(pkgToDelete.exists()==false){
			System.out.println("Package doesn't exist.");
			return;
		}
		pkgToDelete.delete();
		System.out.println("Removing from package list...");
		if(packageListFile.equals(" ")){
			if(new File("bin/packages.ls").exists()==false){
				new File("bin/packages.ls").createNewFile();
			}
			packageListFile="bin/packages.ls";
		}
		File finalPackageFile=new File(packageListFile);
		removePackageFromPackagels(finalPackageFile,pkID);
		System.out.println("Done.");
	}
	private static void removePackageFromPackagels(File packageFile, String packageID) throws IOException{
		ArrayList<String> content=sourcesToArrayList(packageFile);
		makeBlank(packageFile);
		FileWriter append=new FileWriter(packageFile,true);
		for(int x=0;x<content.size();x++){
			if(content.get(x).equals(packageID)==false){
				append.write(content.get(x)+"\n");
			}
		}
		append.close();
	}
	public static void run(String s) throws Exception,IOException{
		String option=s.split(" ")[1];
		File defaultSources=new File("bin/sources.ffup");
		if(sourceFileLocation.equals(" ")){
			if(defaultSources.exists()==false){
			defaultSources.createNewFile();
			FileWriter wr=new FileWriter(defaultSources);
			wr.write("https://dfmaaa.github.io/pckman\n");
			wr.close();
		   }
			sourceFileLocation="bin/sources.ffup";
		}
		if(option.equals("install")){
		ArrayList<String> sourcesToSearch=sourcesToArrayList(new File(sourceFileLocation));
		boolean sourceOption=exists(s.split(" "),"-s");
		boolean choiceSource=exists(s.split(" "),"-c");
		boolean askBeforeInstallation=exists(s.split(" "),"-a");
		String sourceOrBin=sourceOption ? "/source/" : "/bin/";
		String classOrJava=sourceOption ? ".java" : ".class";
		String pkgName=s.split(" ")[s.split(" ").length-1];
			if(choiceSource){
				ArrayList<String> choices=new ArrayList<String>();
				for(int x=0;x<sourcesToSearch.size();x++){
					if(getBooleanExistence(new URL(sourcesToSearch.get(x)+sourceOrBin+pkgName+classOrJava))){
						choices.add(sourcesToSearch.get(x));
					}
				}
				if(choices.size()>0){
				for(int x=0;x<choices.size();x++){
					System.out.println(x+1+"."+choices.get(x));
				}
				Scanner intput=new Scanner(System.in);
				System.out.println(" ");
				System.out.println("Choose the source you want to download from.(Integer value) 1 to "+choices.size());
				int inpt=intput.nextInt()-1;
				while(inpt<0){
					System.out.println("Input should be bigger than 0. Enter integer again.");
					inpt=intput.nextInt()-1;
				 }
				 System.out.println("Installing...");
				 installPackages(choices.get(inpt)+sourceOrBin+pkgName+classOrJava);  
			    }
			}
			else{
			 for(int x=0;x<sourcesToSearch.size();x++){
					if(getBooleanExistence(new URL(sourcesToSearch.get(x)+sourceOrBin+pkgName+classOrJava))){
						if(askBeforeInstallation==false){
							System.out.println("Source: "+ sourcesToSearch.get(x));
							System.out.println("Installing...");
							installPackages(sourcesToSearch.get(x)+sourceOrBin+pkgName+classOrJava);
						}
						else{
							System.out.println("Source found: "+sourcesToSearch.get(x));
							System.out.println("Continue?(Y/N)");
							Scanner input=new Scanner(System.in);
						    if(input.nextLine().toLowerCase().equals("n")){
								continue;
							}
							System.out.println("Installing...");
							installPackages(sourcesToSearch.get(x)+sourceOrBin+pkgName+classOrJava);
						}
					  }
	                }	
	              }
			    }
			    else if(option.equals("uninstall")){
					uninstallPackages(s.split(" ")[s.split(" ").length-1]);
				}
				else if(option.equals("list")){
				  if(packageListFile.equals(" ")){
					  File defaultf=new File("bin/packages.ls");
					  if(defaultf.exists()==false){
						  defaultf.createNewFile();
					  }
					  packageListFile="bin/packages.ls";
				  }
				  File pkgLsFile=new File(packageListFile);
				  System.out.println("Reading "+pkgLsFile.getAbsolutePath());
				  ArrayList<String> getPkgs=sourcesToArrayList(pkgLsFile);
				  for(int x=0;x<getPkgs.size();x++){
					  System.out.println(getPkgs.get(x));
				  }	
				}
				else if(option.equals("sources")){
					File srcFile=new File(sourceFileLocation);
					System.out.println("Reading " + srcFile.getAbsolutePath());
					ArrayList<String> getSrc=sourcesToArrayList(srcFile);
					for(int x=0;x<getSrc.size();x++){
						System.out.println(getSrc.get(x));
					}
				}
				else{
					System.out.println("Invalid option.");
				 }
               }
             }
