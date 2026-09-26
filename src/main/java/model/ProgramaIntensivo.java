package model;

public class ProgramaIntensivo extends Programa {

    public ProgramaIntensivo(String codigo, String nombre, String idioma, String descripcion,
                             int duracionMeses, double valorMensual, EstadoPrograma estado,
                             Modalidad modalidad, int cupos) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad, cupos);
    }

    @Override
    public double calcularValor() {

        return this.duracionMeses * this.valorMensual;
    }

    @Override
    public String obtenerBeneficios() {
        return "Acceso a clubes de conversación y material complementario.";
    }
}