package model;

public class ServicioEntregaInsumos {

    public String prepararEntrega(FabricaInsumos fabrica) {
        Material material = fabrica.crearMaterial();
        Carne carne = fabrica.crearCarne();

        return "Insumos preparados:\n"
                + "- " + material.getDescripcion() + "\n"
                + "- " + carne.getDescripcion();
    }
}