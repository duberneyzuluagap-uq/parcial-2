package co.edu.uniquindio.poo.model;

public class MantenimientoPreventivo extends  Servicio {
    private EquipoTecnologico equipo;

    public MantenimientoPreventivo(String codigo, String nombre, String descripcion, double tarifaBase, EquipoTecnologico equipo) {
        super(codigo, nombre, descripcion, tarifaBase);
        this.equipo = equipo;
    }

    @Override
    public double calcularCostoServicio() {
        if (equipo instanceof ComputadorPortatil) return 60_000;
        if (equipo instanceof ComputadorEscritorio) return 70_000;
        if (equipo instanceof TelefonoInteligente) return 40_000;
        return tarifaBase;
    }

}
