package co.edu.uniquindio.poo.model;

public class MantenimientoPreventivo extends  Servicio {
    public MantenimientoPreventivo(String codigo, String nombre, String descripcion, double tarifaBase) {
        super(codigo, nombre, descripcion, tarifaBase);
    }

    @Override
    public double calcularCostoServicio() {
        return 0;
    }

}
