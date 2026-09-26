package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matricula {
    private final long numero;
    private final Estudiante estudiante;
    private final Programa programa;
    private final LocalDate fechaInicio;

    // Opcionales
    private final Docente docenteTutor;
    private final List<ServicioAdicional> servicios;
    private final double descuento; // Representado como porcentaje (0.0 a 0.30)
    private final String observaciones;

    // Constructor privado: la única puerta de entrada es el Builder
    private Matricula(Builder b) {
        this.estudiante = b.estudiante;
        this.programa = b.programa;
        this.fechaInicio = b.fechaInicio;
        this.docenteTutor = b.docenteTutor;
        // Se crea una lista inmodificable para proteger la encapsulación
        this.servicios = Collections.unmodifiableList(new ArrayList<>(b.servicios));
        this.descuento = b.descuento;
        this.observaciones = b.observaciones;

        // conecta con el Singleton: se asigna el número automático al final de la creación
        this.numero = ConsecutivoMatricula.getInstancia().siguiente();
    }

    // --- Lógica Financiera (RN-06) ---

    public double calcularSubtotal() {
        double totalPrograma = programa.calcularValor();
        double totalServicios = 0;

        for (ServicioAdicional servicio : servicios) {
            totalServicios += servicio.getPrecio();
        }
        return totalPrograma + totalServicios;
    }

    public double calcularDescuentoAplicado() {
        // El descuento se aplica solo sobre el valor del programa, no sobre los servicios
        return programa.calcularValor() * descuento;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuentoAplicado();
    }

    // --- Getters ---
    public long getNumero() { return numero; }
    public Estudiante getEstudiante() { return estudiante; }
    public Programa getPrograma() { return programa; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public Docente getDocenteTutor() { return docenteTutor; }
    public List<ServicioAdicional> getServicios() { return servicios; }
    public double getDescuento() { return descuento; }
    public String getObservaciones() { return observaciones; }


    //BUILDER
    public static class Builder {
        // Atributos obligatorios
        private Estudiante estudiante;
        private Programa programa;
        private LocalDate fechaInicio;

        // Atributos opcionales inicializados con valores por defecto
        private Docente docenteTutor = null;
        private List<ServicioAdicional> servicios = new ArrayList<>();
        private double descuento = 0.0;
        private String observaciones = "";


        public Builder conEstudiante(Estudiante estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public Builder conPrograma(Programa programa) {
            this.programa = programa;
            return this;
        }

        public Builder conFechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder conDocenteTutor(Docente docenteTutor) {
            this.docenteTutor = docenteTutor;
            return this;
        }

        public Builder agregarServicio(ServicioAdicional servicio) {
            this.servicios.add(servicio);
            return this;
        }

        public Builder conDescuento(double porcentajeDescuento) {
            this.descuento = porcentajeDescuento;
            return this;
        }

        public Builder conObservaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }


        // Método final que valida y ensambla la Matrícula

        public Matricula build() {
            // RN-01: Validaciones de datos obligatorios
            if (this.estudiante == null) {
                throw new IllegalStateException("La matrícula requiere un estudiante.");
            }
            if (this.programa == null) {
                throw new IllegalStateException("No puede existir una matrícula sin programa.");
            }
            if (this.fechaInicio == null) {
                throw new IllegalStateException("La matrícula requiere una fecha de inicio.");
            }

            // RN-02: Validación del límite de descuento
            if (this.descuento < 0.0 || this.descuento > 0.30) {
                throw new IllegalStateException("El descuento no puede ser inferior a 0% ni superar el 30% del valor del programa.");
            }

            // Si todas las validaciones pasan, se crea y retorna el objeto final
            return new Matricula(this);
        }

    }

}


