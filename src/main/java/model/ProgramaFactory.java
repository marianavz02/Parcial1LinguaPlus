package model;

public abstract class ProgramaFactory {
    public abstract Programa crearPrograma(String codigo, String nombre, String idioma, String descripcion,
                                           int duracionMeses, double valorMensual, EstadoPrograma estado,
                                           Modalidad modalidad, int cupos);
}
