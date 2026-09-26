package model;

import java.time.LocalDate;

public class Estudiante extends Usuario {
    private int edad;
    private LocalDate fechaRegistro;

    public Estudiante(String indentificacion, String nombreCompleto, String telefono, String correo, int edad, LocalDate fechaRegistro) {
        super(indentificacion, nombreCompleto, telefono, correo);
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }


    // NUMERO PERFECTO
    public boolean esTelefonoPerfecto(){
        try {
            long numero = Long.parseLong(this.telefono);

            if (numero <= 1) {
                return false;
            }

            long sumaDivisores = 1;

            for (long i = 2; i <= Math.sqrt(numero); i++) {
                if (numero % i == 0) {
                    sumaDivisores += i;
                    long divisorPar = numero / i;

                    if (i != divisorPar) {
                        sumaDivisores += divisorPar;
                    }
                }
            }
            return sumaDivisores == numero;
        }catch (NumberFormatException e){
            return false;
        }

    }
}

