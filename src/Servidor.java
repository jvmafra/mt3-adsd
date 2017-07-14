import java.util.LinkedList;
import java.util.Queue;


public class Servidor {

    private Queue<Fregues> fila1;
    private Queue<Fregues> fila2;
    private Fregues freguesTratado;
    private int nextInFila1;
    private int nextInFila2;
    private int nextOut;
    private boolean isOcupado;
    private final boolean LIVRE = false;

    public Servidor() { // inicia servidor com estado livre, 1 fregues em cada fila

    	Util.printaMensagem("Iniciando servidor...");
                
        fila1 = new LinkedList<>();
        fila2 = new LinkedList<>();
        nextInFila1 = 0;
        nextInFila2 = 0;
        nextOut = 0;
        isOcupado = LIVRE;

        Fregues freguesFila1 = new Fregues(0);
        Fregues freguesFila2 = new Fregues(1);
        addFreguesFila1(freguesFila1);
        addFreguesFila2(freguesFila2);

    	Util.printaMensagem("Escalonando proxima chegada classe 1...");
        escalonaProximaChegadaFila1();
    	Util.printaMensagem("Chegada classe 1 em " + getNextInFila1() + " unidades de tempo");
    	Util.printaMensagem("Escalonando proxima chegada classe 2...");
        escalonaProximaChegadaFila2();
    	Util.printaMensagem("Chegada classe 2 em " + getNextInFila2() + " unidades de tempo");
    }


    public void addFreguesFila1(Fregues fregues){
        fila1.add(fregues);
    }

    public Fregues trataFreguesFila1() {
    	Util.printaMensagem("Tratando fregues da fila 1...");
    	Fregues f = fila1.poll();
    	Util.printaMensagem("Fregues id = " + f.getId());

        this.freguesTratado = f;

        return f;
    }

    public void addFreguesFila2(Fregues fregues){
        fila2.add(fregues);
    }

    public Fregues trataFreguesFila2() {
    	Util.printaMensagem("Tratando fregues da fila 2...");
    	Fregues f = fila2.poll();
    	Util.printaMensagem("Fregues id = " + f.getId());
        
        this.freguesTratado = f;
    	
        return f;
    }

    public Queue<Fregues> getFila1() {
        return fila1;
    }

    public Queue<Fregues> getFila2() {
        return fila2;
    }

    public int getNextInFila1() {
        return nextInFila1;
    }

    public int getNextInFila2() {
        return nextInFila2;
    }

    public int getNextOut() {
        return nextOut;
    }

    public void setNextInFila1(int nextInFila1) {
        this.nextInFila1 = nextInFila1;
    }

    public void setNextInFila2(int nextInFila2) {
        this.nextInFila2 = nextInFila2;
    }

    public void setNextOut(int nextOut) {
        this.nextOut = nextOut;
    }

    public void escalonaProximaChegadaFila1() {
        setNextInFila1(nextInFila1 + Util.geraChegadaClasse1());
    	Util.printaMensagem("Proxima chegada no tempo " + this.getNextInFila1());
    }

    public void setEstado(boolean isOcupado){
        this.isOcupado = isOcupado;
    }

    public boolean isOcupado(){
        return isOcupado;
    }

    public void escalonaProximaChegadaFila2() {
        setNextInFila2(nextInFila2 + Util.geraChegadaClasse2());
        
    	Util.printaMensagem("Proxima chegada no tempo " + this.getNextInFila2());
    }

    public void escalonaFimDoServico(int tempoAtual) {
        setNextOut(tempoAtual + Util.geraSaida());
    	Util.printaMensagem("O servico terminara no tempo " + this.getNextOut());
    }

    @Override
    public String toString(){
    	String status = "LIVRE";
    	if (isOcupado) { 
    		status = "OCUPADO";
    	}
    	
        String retorno = "Estado do servidor: " + status + "\n"; 
        retorno += "FILA 1:" + "\n";
        retorno += "[";
        for (Fregues f: fila1){
            retorno += f.toString() + ",";
        }
        retorno += "]";
        retorno += "\n";

        retorno += "FILA 2:" + "\n";
        retorno += "[";
        for (Fregues f: fila2){
            retorno += f.toString() + ",";
        }
        retorno += "]";
        
        retorno += "\nFreguês que está sendo tratado: " + this.freguesTratado.toString();

        return retorno;
    }
}
