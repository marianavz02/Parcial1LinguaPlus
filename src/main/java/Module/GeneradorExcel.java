package model;

public class GeneradorExcel extends GeneradorComprobante {
    @Override
    protected FormatoComprobante crearFormato() {
        return new ComprobanteExcel();
    }
}