import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Util {

    private static Random r = new Random();
    private static PrintWriter writer;
    private static String stringToWrite = "";
    
    public static void printaMensagem(String mensagem) {
        System.out.println(mensagem);
        stringToWrite += mensagem + "\n";
    }
    
    public static void closeWriter(){ 
        File file = new File("output.txt");
       
        try{
            writer = new PrintWriter(file);
            writer.println(stringToWrite);
        	writer.close();
        } catch (IOException e) {}
    	
    }

    public static int geraChegadaClasse1(){
        return r.nextInt(11-1) + 1;
    }

    public static int geraChegadaClasse2(){
        return r.nextInt(6-1) + 1;
    }

    public static int geraSaida(){
        return r.nextInt(8-3) + 3;
    }
}
