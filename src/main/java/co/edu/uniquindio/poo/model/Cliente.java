package co.edu.uniquindio.poo.model;

import java.util.*;

public class Cliente extends  Persona{
    private String telefono;
    private String correoElectronico;
    private String direccionResidencia;
    private Map<String,OrdenServicio> listaOrdenes = new HashMap<>();
    private List<EquipoTecnologico> listaEquipos = new ArrayList<>();


    public Cliente(String documento, String nombre, String telefono, String correoElectronico, String direccionResidencia) {
        super(documento, nombre);
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public Map<String, OrdenServicio> getListaOrdenes() {
        return Collections.unmodifiableMap(listaOrdenes);
    }

    public void setListaOrdenes(Map<String, OrdenServicio> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<EquipoTecnologico> getListaEquipos() {
        return Collections.unmodifiableList(listaEquipos);
    }

    public void setListaEquipos(List<EquipoTecnologico> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
}
