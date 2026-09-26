package model;

import model.Matricula;

public abstract class GeneradorComprobante {

    // El "Factory Method" que cada subclase debe implementar
    protected abstract FormatoComprobante crearFormato();

    public final String emitir(Matricula matricula) {
        FormatoComprobante formato = crearFormato();

        return "=== ACADEMIA LINGUAPLUS ===\n" +
                formato.generar(matricula) +
                "\n=== Gracias por confiar en nosotros ===";
    }
}