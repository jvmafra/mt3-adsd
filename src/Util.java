import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;

public class Util {

    private static Random r = new Random();

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
