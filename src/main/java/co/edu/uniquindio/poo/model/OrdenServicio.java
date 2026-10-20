package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenServicio {
    private String codigo;
    private LocalDate fecha;
    private String observaciones;
    private EstadoOrden estadoOrden;
    private List<DetalleServicio> listaDetalles = new ArrayList<>();

    public OrdenServicio(String codigo, LocalDate fecha, String observaciones, EstadoOrden estadoOrden) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.estadoOrden = estadoOrden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public List<DetalleServicio> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleServicio> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    //CRUD DETALLE_SERVICIO
    public Optional<DetalleServicio> buscarDetalleServicio(String codigoDetalle) {
        Optional<DetalleServicio> detalleOptional = listaDetalles.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigoDetalle))
                .findFirst();
        return detalleOptional;
    }

    public String registrarDetalleServicio(DetalleServicio detalle) {
        Optional<DetalleServicio> detalleOptional = buscarDetalleServicio(detalle.getCodigo());
        if (detalleOptional.isPresent()) {
            return "El detalle de servicio " + detalle.getCodigo() + " ya esta registrado en la orden";
        }
        listaDetalles.add(detalle);
        return "Detalle de servicio agregado correctamente";
    }

    public String actualizarEstadoDetalle(String codigoDetalle, EstadoOrden nuevoEstado) {
        Optional<DetalleServicio> detalleOptional = buscarDetalleServicio(codigoDetalle);
        if (detalleOptional.isPresent()) {
            DetalleServicio detalle = detalleOptional.get();
            detalle.setEstadoIntervencion(nuevoEstado);
            return "Estado del detalle actualizado a " + nuevoEstado;
        }
        return "No se encontro el detalle " + codigoDetalle + " en la orden";
    }

    public String eliminarDetalleServicio(String codigoDetalle) {
        Optional<DetalleServicio> detalleOptional = buscarDetalleServicio(codigoDetalle);
        if (detalleOptional.isPresent()) {
            listaDetalles.remove(detalleOptional.get());
            return "Detalle de servicio eliminado correctamente";
        }
        return "No se encontro el detalle " + codigoDetalle + " para eliminar";
    }

    public double calcularValorTotalOrden(){
        double valorTotal = 0;
        for (DetalleServicio d: listaDetalles){
            valorTotal += d.calcularValorDetalle();
        }
        return valorTotal;
    }

    public String finalizarOrden(){
        if (listaDetalles.isEmpty()) {
            return "La orden no se puede finalizar porque no tiene servicios registrados";
        }
        for (DetalleServicio d: listaDetalles){
            if (!d.getEstadoIntervencion().equals(EstadoOrden.FINALIZADA)){
                return "La orden no esta finalizada aun ";
            }
        }
        setEstadoOrden(EstadoOrden.FINALIZADA);
        return "La orden ha finalizado con exito con un precio de: $" + calcularValorTotalOrden();

    }
}
