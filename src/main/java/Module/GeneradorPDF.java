package model;

public class GeneradorPDF extends GeneradorComprobante {
    @Override
    protected FormatoComprobante crearFormato() {
        return new ComprobantePDF();
    }
}