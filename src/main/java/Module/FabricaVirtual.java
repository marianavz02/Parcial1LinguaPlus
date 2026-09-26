package model;

public class FabricaVirtual implements FabricaInsumos {
    @Override
    public Material crearMaterial() {
        return new LicenciaPlataforma();
    }

    @Override
    public Carne crearCarne() {
        return new CarneDigital();
    }
}