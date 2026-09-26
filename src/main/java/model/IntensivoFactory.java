package model;

public class IntensivoFactory extends ProgramaFactory {
    @Override
    public Programa crearPrograma(String codigo, String nombre, String idioma, String descripcion,
                                  int duracionMeses, double valorMensual, EstadoPrograma estado,
                                  Modalidad modalidad, int cupos) {
        return new ProgramaIntensivo(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad, cupos);
    }
}