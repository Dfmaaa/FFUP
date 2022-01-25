package bin;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Class;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
public class exec {
    private static String[] read(File f) throws IOException {
        ArrayList<String> a=new ArrayList<String>();
        try (FileReader fileStream = new FileReader(f);
             BufferedReader bufferedReader = new BufferedReader(fileStream)) {
            String line = null;
            while ((line = bufferedReader.readLine())!= null) {
                a.add(line);
            }
        }
        return altarr(a);
    }
    private static String[] altarr(ArrayList<String> a){
        String[] ret=new String[a.size()];
        for(int x=0;x<ret.length;x++){
            ret[x]=a.get(x);
        }
        return ret;
    }
    public static void run(String s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if(new File(s.split(" ")[1]).exists()) {
            String[] getCommands = read(new File(s.split(" ")[1]));
            for (int x = 0; x < getCommands.length; x++) {
                try {
                    String className = "bin." + getCommands[x].split(" ")[0];
                    Class<Object> clazz = (Class<Object>) Class.forName(className);
                    Object instance = clazz.getConstructor().newInstance();
                    clazz.getMethod("run", String.class).invoke(instance, getCommands[x]);
                } catch (ClassNotFoundException n) {
                    System.out.println("Command not found.");
                } catch (Exception e) {
                }
            }
        }
        else{
            System.out.println("File does not exist.");
        }
    }
}
