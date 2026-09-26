package model;

public abstract class Usuario {
    protected String indentificacion;
    protected String nombreCompleto;
    protected String telefono;
    protected String correo;

    public Usuario(String indentificacion, String nombreCompleto, String telefono, String correo) {
        this.indentificacion = indentificacion;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getIndentificacion() {
        return indentificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

}
