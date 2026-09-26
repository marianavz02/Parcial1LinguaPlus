package model;


public abstract class Programa implements Cloneable {
    protected String codigo;
    protected String nombre;
    protected String idioma;
    protected String descripcion;
    protected int duracionMeses;
    protected double valorMensual;
    protected EstadoPrograma estado;
    protected Modalidad modalidad;
    protected int cupos;

    public Programa(String codigo, String nombre, String idioma, String descripcion,
                    int duracionMeses, double valorMensual, EstadoPrograma estado,
                    Modalidad modalidad, int cupos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.modalidad = modalidad;
        this.cupos = cupos;
    }

    public abstract double calcularValor();
    public abstract String obtenerBeneficios();

    // Gestión de cupos
    public void descontarCupo() {
        if (this.cupos <= 0) {
            throw new IllegalStateException("No hay cupos disponibles para el programa: " + nombre);
        }
        this.cupos--;
    }

    public int getCupos() { return cupos; }
    public double getValorMensual() { return valorMensual; }
    public Modalidad getModalidad() { return modalidad; }
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }

    /**
     * Patrón Prototype: Se clona a sí mismo para independizar los cupos
     * al abrir un nuevo periodo académico.
     */
    @Override
    public Programa clone() {
        try {
            // Como los atributos son primitivos, Strings y Enums, la copia superficial de super.clone() es suficiente
            return (Programa) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Fallo al clonar Programa", e);
        }
    }
}