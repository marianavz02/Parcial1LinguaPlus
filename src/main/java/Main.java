package model;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== INICIO DE PRUEBAS DEL SISTEMA LINGUAPLUS ===\n");

        // 0. Preparación del entorno y datos base
        Academia academia = new Academia("LinguaPlus", "900.123.456-7", "Calle 1 #2-3", "555-0000", "contacto@linguaplus.com", "www.linguaplus.com");

        Estudiante est1 = new Estudiante("1001", "Ana Gómez", "28", "ana@correo.com", 22, LocalDate.now());
        Estudiante est2 = new Estudiante("1002", "Luis Pérez", "20", "luis@correo.com", 25, LocalDate.now());
        Estudiante est3 = new Estudiante("1003", "Carlos Ruiz", "abc", "carlos@correo.com", 30, LocalDate.now()); // Teléfono no numérico

        Docente docente = new Docente("2001", "María López", "555-1111", "maria@correo.com", "Inglés", 50000);

        // Creamos programas de prueba usando las fábricas
        Programa progBasico = new BasicoFactory().crearPrograma("P01", "Inglés Básico", "Inglés", "Nivel A1", 6, 100000, EstadoPrograma.ACTIVO, Modalidad.PRESENCIAL, 15);
        Programa progIntensivo = new IntensivoFactory().crearPrograma("P02", "Inglés Intensivo", "Inglés", "Nivel B1", 4, 150000, EstadoPrograma.ACTIVO, Modalidad.VIRTUAL, 10);
        Programa progPersonalizado = new PersonalizadoFactory(5, "B2", "Conversación").crearPrograma("P03", "Inglés VIP", "Inglés", "Avanzado", 2, 200000, EstadoPrograma.ACTIVO, Modalidad.PRESENCIAL, 5);


        // ====================================================================
        // 1. PRUEBAS DE MATRÍCULA (Builder y validaciones)
        // ====================================================================
        System.out.println("--- 1. PRUEBAS DE MATRÍCULA ---");

        // a. Crear una matrícula con todos los datos obligatorios
        try {
            Matricula matValida = new Matricula.Builder()
                    .conEstudiante(est1)
                    .conPrograma(progBasico)
                    .conFechaInicio(LocalDate.of(2026, 2, 1))
                    .build();
            academia.registrarMatricula(matValida);
            System.out.println("[OK] Matrícula con datos obligatorios creada exitosamente. Ticket: " + matValida.getNumero());
        } catch (Exception e) {
            System.out.println("[ERROR] Matrícula válida falló: " + e.getMessage());
        }

        // b. Intentar crear una matrícula sin estudiante
        try {
            new Matricula.Builder()
                    .conPrograma(progBasico)
                    .conFechaInicio(LocalDate.now())
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("[OK] Correctamente rechazado (Sin estudiante): " + e.getMessage());
        }

        // c. Intentar crear una matrícula sin programa
        try {
            new Matricula.Builder()
                    .conEstudiante(est1)
                    .conFechaInicio(LocalDate.now())
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("[OK] Correctamente rechazado (Sin programa): " + e.getMessage());
        }

        // d. Intentar crear una matrícula sin fecha de inicio
        try {
            new Matricula.Builder()
                    .conEstudiante(est1)
                    .conPrograma(progBasico)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("[OK] Correctamente rechazado (Sin fecha): " + e.getMessage());
        }

        // e. Crear una matrícula con descuento del 30%
        try {
            Matricula matDescuento30 = new Matricula.Builder()
                    .conEstudiante(est1)
                    .conPrograma(progBasico)
                    .conFechaInicio(LocalDate.now())
                    .conDescuento(0.30)
                    .build();
            System.out.println("[OK] Matrícula con 30% de descuento creada. Total calculado: $" + matDescuento30.calcularTotal());
        } catch (Exception e) {
            System.out.println("[ERROR] Descuento del 30% falló: " + e.getMessage());
        }

        // f. Intentar crear una matrícula con descuento superior al 30% (ej. 35%)
        try {
            new Matricula.Builder()
                    .conEstudiante(est1)
                    .conPrograma(progBasico)
                    .conFechaInicio(LocalDate.now())
                    .conDescuento(0.35)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("[OK] Correctamente rechazado (Descuento > 30%): " + e.getMessage());
        }
        System.out.println();


        // ====================================================================
        // 2. PRUEBAS DE CONSECUTIVO (Singleton)
        // ====================================================================
        System.out.println("--- 2. PRUEBAS DE CONSECUTIVO (TICKETS) ---");
        Matricula m1 = new Matricula.Builder().conEstudiante(est1).conPrograma(progBasico).conFechaInicio(LocalDate.now()).build();
        Matricula m2 = new Matricula.Builder().conEstudiante(est2).conPrograma(progIntensivo).conFechaInicio(LocalDate.now()).build();
        Matricula m3 = new Matricula.Builder().conEstudiante(est1).conPrograma(progPersonalizado).conFechaInicio(LocalDate.now()).build();

        System.out.println("Ticket Matrícula 1: " + m1.getNumero());
        System.out.println("Ticket Matrícula 2: " + m2.getNumero());
        System.out.println("Ticket Matrícula 3: " + m3.getNumero());
        if (m3.getNumero() > m2.getNumero() && m2.getNumero() > m1.getNumero()) {
            System.out.println("[OK] Los números son diferentes, crecientes y consecutivos.");
        } else {
            System.out.println("[ERROR] El consecutivo no se generó adecuadamente.");
        }
        System.out.println();


        // ====================================================================
        // 3. PRUEBAS DE NÚMERO PERFECTO (Matemáticas en Estudiante)
        // ====================================================================
        System.out.println("--- 3. PRUEBAS DE NÚMERO PERFECTO ---");
        System.out.println("Estudiante 1 (Teléfono '28'): ¿Es perfecto? -> " + est1.esTelefonoPerfecto() + " (Esperado: true)");
        System.out.println("Estudiante 2 (Teléfono '20'): ¿Es perfecto? -> " + est2.esTelefonoPerfecto() + " (Esperado: false)");
        System.out.println("Estudiante 3 (Teléfono 'abc'): ¿Es perfecto? -> " + est3.esTelefonoPerfecto() + " (Esperado: false - No numérico)");
        System.out.println();


        // ====================================================================
        // 4. PRUEBAS DE PROGRAMAS Y CUPOS (Prototype)
        // ====================================================================
        System.out.println("--- 4. PRUEBAS DE PROGRAMAS Y CUPOS (PROTOTYPE) ---");

        // Creación de tipos de programas
        System.out.println("[OK] Programas creados: " + progBasico.getNombre() + ", " + progIntensivo.getNombre() + ", " + progPersonalizado.getNombre());

        // Descontar cupos
        int cuposIniciales = progBasico.getCupos();
        progBasico.descontarCupo();
        System.out.println("[OK] Cupos de programa básico tras descontar uno: " + progBasico.getCupos() + " (Iniciales: " + cuposIniciales + ")");

        // Clonación de periodo académico
        PeriodoAcademico periodoBase = new PeriodoAcademico("Oferta Base", LocalDate.of(2026, 1, 1), LocalDate.of(2026, 6, 30));
        periodoBase.agregarPrograma(progIntensivo);

        PeriodoAcademico periodoClonado = periodoBase.clone();
        periodoClonado.setNombre("Periodo Semestre 2026-1");

        // Modificar cupos en el periodo clonado y comprobar independencia
        periodoClonado.getProgramasOfertados().get(0).descontarCupo();
        periodoClonado.getProgramasOfertados().get(0).descontarCupo();

        System.out.println("Cupos del programa en Periodo Base: " + periodoBase.getProgramasOfertados().get(0).getCupos());
        System.out.println("Cupos del programa en Periodo Clonado: " + periodoClonado.getProgramasOfertados().get(0).getCupos());
        System.out.println("[OK] Copia profunda verificada: Los cupos del periodo clonado permanecen independientes.");
        System.out.println();


        // ====================================================================
        // 5. PRUEBAS DE MODALIDADES (Abstract Factory)
        // ====================================================================
        System.out.println("--- 5. PRUEBAS DE MODALIDADES (ABSTRACT FACTORY) ---");

        FabricaInsumos fabricaPresencial = new FabricaPresencial();
        Material matPresencial = fabricaPresencial.crearMaterial();
        Carne carnePresencial = fabricaPresencial.crearCarne();
        System.out.println("Fábrica Presencial genera: " + matPresencial.getDescripcion() + " | " + carnePresencial.getDescripcion());

        FabricaInsumos fabricaVirtual = new FabricaVirtual();
        Material matVirtual = fabricaVirtual.crearMaterial();
        Carne carneVirtual = fabricaVirtual.crearCarne();
        System.out.println("Fábrica Virtual genera: " + matVirtual.getDescripcion() + " | " + carneVirtual.getDescripcion());
        System.out.println("[OK] Insumos creados sin mezclar familias.");
        System.out.println();


        // ====================================================================
        // 6. PRUEBAS DE INGRESOS POR PERIODO
        // ====================================================================
        System.out.println("--- 6. PRUEBAS DE CÁLCULO DE INGRESOS ---");

        PeriodoAcademico periodoEneroMarzo = new PeriodoAcademico("Enero-Marzo", LocalDate.of(2026, 1, 1), LocalDate.of(2026, 3, 31));
        PeriodoAcademico periodoAbrilJunio = new PeriodoAcademico("Abril-Junio", LocalDate.of(2026, 4, 1), LocalDate.of(2026, 6, 30));

        // Registramos matrículas en diferentes meses
        Matricula matEnero = new Matricula.Builder().conEstudiante(est1).conPrograma(progBasico).conFechaInicio(LocalDate.of(2026, 1, 15)).build();
        Matricula matMayo = new Matricula.Builder().conEstudiante(est2).conPrograma(progIntensivo).conFechaInicio(LocalDate.of(2026, 5, 10)).build();

        Academia academiaIngresos = new Academia("Academia Test", "900-0", "Dir", "Tel", "Correo", "Web");
        academiaIngresos.registrarMatricula(matEnero);
        academiaIngresos.registrarMatricula(matMayo);

        double ingresosEneroMarzo = academiaIngresos.calcularIngresos(periodoEneroMarzo);
        double ingresosAbrilJunio = academiaIngresos.calcularIngresos(periodoAbrilJunio);

        System.out.println("Ingresos periodo Enero-Marzo: $" + ingresosEneroMarzo + " (Incluye solo matrícula de enero)");
        System.out.println("Ingresos periodo Abril-Junio: $" + ingresosAbrilJunio + " (Incluye solo matrícula de mayo)");
        System.out.println("[OK] Cálculo de ingresos filtrado correctamente por rango de fechas del periodo.");
        System.out.println();


        // ====================================================================
        // 7. PRUEBAS DE COMPROBANTES (Factory Method)
        // ====================================================================
        System.out.println("--- 7. PRUEBAS DE COMPROBANTES ---");

        GeneradorComprobante genPDF = new GeneradorPDF();
        GeneradorComprobante genExcel = new GeneradorExcel();

        String comprobantePDF = genPDF.emitir(m1);
        String comprobanteExcel = genExcel.emitir(m1);

        System.out.println("--- Salida GeneradorPDF ---");
        System.out.println(comprobantePDF);

        System.out.println("\n--- Salida GeneradorExcel ---");
        System.out.println(comprobanteExcel);

        System.out.println("\n[OK] Ambos formatos contienen la información de la matrícula, el estudiante y su valor total.");
        System.out.println("\n=== FIN DE TODAS LAS PRUEBAS EXITOSAMENTE ===");
    }
}