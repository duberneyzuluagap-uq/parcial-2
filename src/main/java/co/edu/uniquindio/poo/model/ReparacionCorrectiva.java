package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // CRUD DETAllE_REPUESTO

    public Optional<DetalleRepuesto> buscarDetalleRepuesto(Repuesto repuesto){
        return listaDetallesRepuesto.stream().filter(d->d.getRepuesto().equals(repuesto)).findFirst();
    }

    public String registrarDetalleRepuesto(DetalleRepuesto detalle){
        Optional<DetalleRepuesto> detalleRepuestoOptional = buscarDetalleRepuesto(detalle.getRepuesto());

        if (detalleRepuestoOptional.isPresent()){
            return "Ya esta registrado ese repuesto en el servicio";
        }else if(detalle.getCantidadRepuesto() <= 0){
            return "La cantidad del repuesto debe ser mayor a 0";
        }else if(detalle.getCantidadRepuesto()<= detalle.getRepuesto().getCantidadDisponible() ){
            listaDetallesRepuesto.add(detalle);
            detalle.getRepuesto().descontarExistencias(detalle.getCantidadRepuesto());
            return "Detalle repuesto registrado con exito";
        }else{
            return "No hay cantidad suficiente del repuesto";
        }


    }
}
