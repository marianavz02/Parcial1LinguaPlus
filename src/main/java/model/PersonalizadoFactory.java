package model;

public class PersonalizadoFactory extends ProgramaFactory {

    // Atributos extra que requiere el programa personalizado
    private int sesionesTutor;
    private String nivelIdioma;
    private String objetivos;

    public PersonalizadoFactory(int sesionesTutor, String nivelIdioma, String objetivos) {
        this.sesionesTutor = sesionesTutor;
        this.nivelIdioma = nivelIdioma;
        this.objetivos = objetivos;
    }

    @Override
    public Programa crearPrograma(String codigo, String nombre, String idioma, String descripcion,
                                  int duracionMeses, double valorMensual, EstadoPrograma estado,
                                  Modalidad modalidad, int cupos) {

        return new ProgramaPersonalizado(codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado,
                modalidad, cupos, sesionesTutor,
                nivelIdioma, objetivos);
    }
}