public class Simulador {

    private static Servidor servidor;
    public static int ID = 2;
    public static int TEMPO_SIMULACAO = 10;

    public static void main(String[] args){
        servidor = new Servidor();

        for (int i = 0; i <= TEMPO_SIMULACAO; i++) {
            System.out.println("Tempo = " + i);
            if (i != servidor.getNextInFila1() && i != servidor.getNextInFila2()
                    && i != servidor.getNextOut()) {
                System.out.println("SEM EVENTOS");
            }

            if (i == servidor.getNextOut()) {  //Ha gente para terminar nesse tempo
                if (i == 0) {
                    System.out.printf("INICIO DA SIMULACAO...");
                    System.out.printf("Tratando fregues ja existente na fila...");
                } else {
                    System.out.println("TIPO DO EVENTO: Saida do sistema");
                }
                if (!servidor.getFila1().isEmpty()) {
                    System.out.println("Tratando fregues da fila 1...");
                    Fregues f = servidor.trataFreguesFila1(); // remove fregues da fila
                    System.out.println("Fregues id = " + f.getId());
                    servidor.escalonaFimDoServico(i); // gerando fim do servico para fregues removido
                    System.out.println("O servico terminara no tempo " + servidor.getNextOut());
                    servidor.setEstado(true); // seta estado para ocupado
                } else if (!servidor.getFila2().isEmpty()) {
                    System.out.println("Tratando fregues da fila 2...");
                    Fregues f = servidor.trataFreguesFila2();
                    System.out.println("Fregues id = " + f.getId());
                    servidor.escalonaFimDoServico(i);
                    System.out.println("O servico terminara no tempo " + servidor.getNextOut());
                    servidor.setEstado(true);
                } else {
                    System.out.println("Nao ha ninguem nas filas. Servidor livre...");
                    servidor.setEstado(false); // se as duas estao vazias, o estado eh livre
                }
            }
            if (i == servidor.getNextInFila1() && i != 0) { // trata nova chegada fila1
                System.out.println("TIPO DO EVENTO: Chegada no sistema classe 1");
                System.out.println("Escalonando proxima chegada classe 1...");
                servidor.escalonaProximaChegadaFila1(); // escalona proxima chegada
                System.out.println("Proxima chegada no tempo " + servidor.getNextInFila1());

                if (servidor.isOcupado()) {
                    System.out.println("Servidor ocupado. Colocando fregues na fila 1.");
                    Fregues novoFregues = new Fregues(ID++);
                    servidor.addFreguesFila1(novoFregues);
                } else {
                    System.out.println("Servidor livre. Tratando fregues...");
                    servidor.setEstado(true); // muda estado para ocupado
                    servidor.escalonaFimDoServico(i); // escalona fim do servico do fregues atual
                }

            }
            if (i == servidor.getNextInFila2() && i != 0) { // trata nova chegada fila2
                System.out.println("TIPO DO EVENTO: Chegada no sistema classe 2");

                System.out.println("Escalonando proxima chegada classe 2...");
                servidor.escalonaProximaChegadaFila2(); // escalona proxima chegada
                System.out.println("Proxima chegada no tempo " + servidor.getNextInFila2());

                if (servidor.isOcupado()) {
                    System.out.println("Servidor ocupado. Colocando fregues na fila 2.");
                    Fregues novoFregues = new Fregues(ID++);
                    servidor.addFreguesFila2(novoFregues);
                } else {
                    System.out.println("Servidor livre. Tratando fregues...");
                    servidor.setEstado(true); // muda estado para ocupado
                    servidor.escalonaFimDoServico(i); // escalona fim do servico do fregues atual
                }
            }


            System.out.println("ESTADO DAS FILAS:");
            System.out.println(servidor.toString());
            System.out.println("\n");

        }

    }
}
