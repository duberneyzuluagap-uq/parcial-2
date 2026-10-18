package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
