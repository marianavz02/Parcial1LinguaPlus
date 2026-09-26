package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeriodoAcademico {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Programa> programasOfertados;

    public PeriodoAcademico(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.programasOfertados = new ArrayList<>();
    }

    public void agregarPrograma(Programa programa) {
        this.programasOfertados.add(programa);
    }




    public String getNombre() { return nombre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public List<Programa> getProgramasOfertados() {
        return Collections.unmodifiableList(programasOfertados);
    }

    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public PeriodoAcademico clone() {
        try {
            // 1. Copia superficial del periodo
            PeriodoAcademico copia = (PeriodoAcademico) super.clone();

            // 2. Nueva lista independiente para la copia
            copia.programasOfertados = new ArrayList<>();

            // 3. Copia profunda: se clona cada programa individualmente
            for (Programa p : this.programasOfertados) {
                // Requiere que la clase Programa también implemente Cloneable y tenga un método clone() público
                copia.programasOfertados.add(p.clone());
            }

            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Fallo al clonar PeriodoAcademico", e);
        }
    }
}
