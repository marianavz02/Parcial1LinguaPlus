package model;

public class Docente extends Usuario{

    private String idiomaEspecialidad;
    private double tarifaPorSecion;

    public Docente(String indentificacion, String nombreCompleto, String telefono, String correo, String idiomaEspecialidad, double tarifaPorSecion) {
        super(indentificacion, nombreCompleto, telefono, correo);
        this.idiomaEspecialidad = idiomaEspecialidad;
        this.tarifaPorSecion = tarifaPorSecion;
    }

    public String getIdiomaEspecialidad() {
        return idiomaEspecialidad;
    }

    public double getTarifaPorSecion() {
        return tarifaPorSecion;
    }
}
