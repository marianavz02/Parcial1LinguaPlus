package model;
public class ProgramaPersonalizado extends Programa {

    private int sesionesTutor;
    private String nivelIdioma;
    private String objetivos;

    public ProgramaPersonalizado(String codigo, String nombre, String idioma, String descripcion,
                                 int duracionMeses, double valorMensual, EstadoPrograma estado,
                                 Modalidad modalidad, int cupos, int sesionesTutor,
                                 String nivelIdioma, String objetivos) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad, cupos);
        this.sesionesTutor = sesionesTutor;
        this.nivelIdioma = nivelIdioma;
        this.objetivos = objetivos;
    }

    @Override
    public double calcularValor() {

        return (this.duracionMeses * this.valorMensual) + (this.sesionesTutor * 50000); // Ejemplo: costo extra por sesión
    }

    @Override
    public String obtenerBeneficios() {
        return "Acompañamiento 1 a 1 con tutor asignado.";
    }

    public int getSesionesTutor() { return sesionesTutor; }
}