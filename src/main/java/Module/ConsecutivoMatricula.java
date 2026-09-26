package model;

public class ConsecutivoMatricula {
    private static final ConsecutivoMatricula INSTANCIA = new ConsecutivoMatricula();

    private long ultimo = 0;
    private ConsecutivoMatricula() {}
    public static ConsecutivoMatricula getInstancia() {
        return INSTANCIA;
    }

    public synchronized long siguiente(){
        return ++ultimo;
    }
}

//se aplico el patron singleton