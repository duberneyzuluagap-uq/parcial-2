package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class ReparacionCorrectiva extends Servicio {
    private int horasTrabjadas;
    private List<DetalleRepuesto> listaDetallesRepuesto = new ArrayList<>();

    public ReparacionCorrectiva(String codigo, String nombre, String descripcion, double tarifaBase, int horasTrabjadas) {
        super(codigo, nombre, descripcion, tarifaBase);
        this.horasTrabjadas = horasTrabjadas;
    }

    @Override
    public double calcularCostoServicio() {
        double manoObra = horasTrabjadas * 45_000;

        for (DetalleRepuesto d: listaDetallesRepuesto){
            manoObra += d.calcularSubTotal();

        }
        return  manoObra;
    }
}
