package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Academia {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    private List<Estudiante> estudiantes;
    private List<Docente> docentes;
    private List<Programa> programasBase;
    private List<PeriodoAcademico> periodos;
    private List<Matricula> matriculas;
    private List<ServicioAdicional> serviciosAdicionales;

    public void registrarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }


    //METODOS DE REGISTRO
    public void registrarDocente(Docente docente) {
        this.docentes.add(docente);
    }

    public void registrarProgramaBase(Programa programa) {
        this.programasBase.add(programa);
    }

    public void registrarPeriodo(PeriodoAcademico periodo) {
        this.periodos.add(periodo);
    }

    public void registrarServicioAdicional(ServicioAdicional servicio) {
        this.serviciosAdicionales.add(servicio);
    }

    public void registrarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }


//INGRESOS
    public double calcularIngresos(PeriodoAcademico periodo) {
        double ingresosTotales = 0.0;

        for (Matricula matricula : matriculas) {
            // Verifica si la fecha de la matrícula está dentro del rango del periodo (inclusive)
            boolean despuesOIgualInicio = !matricula.getFechaInicio().isBefore(periodo.getFechaInicio());
            boolean antesOIgualFin = !matricula.getFechaInicio().isAfter(periodo.getFechaFin());

            if (despuesOIgualInicio && antesOIgualFin) {
                ingresosTotales += matricula.calcularTotal();
            }
        }

        return ingresosTotales;
    }


    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public List<Programa> getProgramasBase() {
        return programasBase;
    }

    public List<PeriodoAcademico> getPeriodos() {
        return periodos;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }
}
