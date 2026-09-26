package model;

public class FabricaPresencial implements FabricaInsumos {
    @Override
    public Material crearMaterial() {
        return new MaterialImpreso();
    }

    @Override
    public Carne crearCarne() {
        return new CarneFisico();
    }
}