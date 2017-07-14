
public class Simulador {

    private static Servidor servidor;
    public static int ID = 2;
    public static int TEMPO_SIMULACAO = 15;
    public static final boolean LIVRE = false;
    public static final boolean OCUPADO = true;
    
    public static void main(String[] args) {
        servidor = new Servidor();

        for (int i = 0; i <= TEMPO_SIMULACAO; i++) {
            Util.printaMensagem("Tempo = " + i);
            if (i != servidor.getNextInFila1() && i != servidor.getNextInFila2()
                    && i != servidor.getNextOut()) {
            	Util.printaMensagem("SEM EVENTOS");
            }

            if (i == servidor.getNextOut()) {  //Ha gente para terminar nesse tempo
                if (i == 0) {
                	Util.printaMensagem("INICIO DA SIMULACAO...");
                	Util.printaMensagem("TIPO DO EVENTO: Saida do sistema");
                } else {
                	Util.printaMensagem("");
                }
                
                if (!servidor.getFila1().isEmpty()) {                   
                    servidor.trataFreguesFila1();                 
                    servidor.escalonaFimDoServico(i); // gerando fim do servico para fregues removido
                    servidor.setEstado(OCUPADO);
                } else if (!servidor.getFila2().isEmpty()) {
                    servidor.trataFreguesFila2();
                    servidor.escalonaFimDoServico(i);
                    servidor.setEstado(OCUPADO);
                } else {
                	Util.printaMensagem("Nao ha ninguem nas filas. Servidor livre...");

                    servidor.setEstado(LIVRE); // se as duas estao vazias, o estado eh livre
                }
            }
            if (i == servidor.getNextInFila1() && i != 0) { // trata nova chegada fila1
                printaNovaChegada("classe 1");
                servidor.escalonaProximaChegadaFila1(); // escalona proxima chegada
                
                if (servidor.isOcupado()) {
                	Util.printaMensagem("Servidor ocupado. Colocando fregues na fila 1.");
                    Fregues novoFregues = new Fregues(ID++);
                    servidor.addFreguesFila1(novoFregues);
                } else {
                	Util.printaMensagem("Servidor livre. Tratando fregues...");
                    servidor.setEstado(OCUPADO);
                    servidor.escalonaFimDoServico(i); // escalona fim do servico do fregues atual
                }
            }
            
            if (i == servidor.getNextInFila2() && i != 0) { // trata nova chegada fila2
                printaNovaChegada("classe 2");
            	servidor.escalonaProximaChegadaFila2(); // escalona proxima chegada

                if (servidor.isOcupado()) {
                	Util.printaMensagem("Servidor ocupado. Colocando fregues na fila 2.");
                    Fregues novoFregues = new Fregues(ID++);
                    servidor.addFreguesFila2(novoFregues);
                } else {
                	Util.printaMensagem("Servidor livre. Tratando fregues...");
                    servidor.setEstado(OCUPADO);
                    servidor.escalonaFimDoServico(i); // escalona fim do servico do fregues atual
                }
            }

        	Util.printaMensagem("ESTADO DAS FILAS:");
        	Util.printaMensagem(servidor.toString());
        	Util.printaMensagem("\n");
        }
        
        Util.closeWriter();

    }

    private static void printaNovaChegada(String classe) { 
    	Util.printaMensagem("TIPO DO EVENTO: Chegada no sistema " + classe);
    	Util.printaMensagem("Escalonando proxima chegada " + classe + "...");
    }
    
}
