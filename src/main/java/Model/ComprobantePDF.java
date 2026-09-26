package model;

import model.Matricula;

public class ComprobantePDF implements FormatoComprobante {
    @Override
    public String generar(Matricula matricula) {
        return "[FORMATO PDF] \n" +
                "Comprobante de Matrícula #" + matricula.getNumero() + "\n" +
                "Estudiante: " + matricula.getEstudiante().getNombreCompleto() + "\n" +
                "Programa: " + matricula.getPrograma().getNombre() + "\n" +
                "Total Pagado: $" + matricula.calcularTotal();
    }
}