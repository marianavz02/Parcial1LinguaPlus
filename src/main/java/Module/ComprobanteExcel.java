package model;

import model.Matricula;

public class ComprobanteExcel implements FormatoComprobante {
    @Override
    public String generar(Matricula matricula) {
        return "numero_matricula;estudiante;programa;total\n" +
                matricula.getNumero() + ";" +
                matricula.getEstudiante().getNombreCompleto() + ";" +
                matricula.getPrograma().getNombre() + ";" +
                matricula.calcularTotal();
    }
}