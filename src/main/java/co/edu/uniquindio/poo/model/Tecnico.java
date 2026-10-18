package co.edu.uniquindio.poo.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Tecnico extends Persona {
    private String codigo;
    private Especialdad especialdad;
    private EstadoLaboral estadoLaboral;
    private List<DetalleServicio> listaDetallesTecnico = new LinkedList<>();

    public Tecnico(String documento, String nombre, String codigo, Especialdad especialdad, EstadoLaboral estadoLaboral) {
        super(documento, nombre);
        this.codigo = codigo;
        this.especialdad = especialdad;
        this.estadoLaboral = estadoLaboral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Especialdad getEspecialdad() {
        return especialdad;
    }

    public void setEspecialdad(Especialdad especialdad) {
        this.especialdad = especialdad;
    }

    public EstadoLaboral getEstadoLaboral() {
        return estadoLaboral;
    }

    public void setEstadoLaboral(EstadoLaboral estadoLaboral) {
        this.estadoLaboral = estadoLaboral;
    }

    public void agregarDetalle(DetalleServicio detalle) {
        if (!listaDetallesTecnico.contains(detalle)) {listaDetallesTecnico.add(detalle);}
    }

    public void quitarDetalle(DetalleServicio detalle) {
        listaDetallesTecnico.remove(detalle);
    }

    public List<DetalleServicio> getListaDetallesTecnico() {
        return Collections.unmodifiableList(listaDetallesTecnico);
    }

    public void setListaDetallesTecnico(List<DetalleServicio> listaDetallesTecnico) {
        this.listaDetallesTecnico = listaDetallesTecnico;
    }
}
