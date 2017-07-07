import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Servidor {

    private Queue<Evento> fila1;
    private Queue<Evento> fila2;
    private int nextInFila1;
    private int nextInFila2;
    private int nextOut;
    private Estado estado;

    public Servidor(){
        fila1 = new LinkedList<>();
        fila2 = new LinkedList<>();
        nextInFila1 = 0;
        nextInFila2 = 0;
        nextOut = 0;
        estado = Estado.LIVRE;

    }


    public void addEventoFila1(Evento evento){
        fila1.add(evento);
    }

    public Evento trataEventoFila1(){
        return fila1.poll();
    }

    public void addEventoFila2(Evento evento){
        fila2.add(evento);
    }

    public Evento trataEventoFila2(){
        return fila2.poll();
    }

    public Queue<Evento> getFila1() {
        return fila1;
    }

    public Queue<Evento> getFila2() {
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

    public Estado getEstado() {
        return estado;
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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
