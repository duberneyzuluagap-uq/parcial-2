package co.edu.uniquindio.poo.model;

import java.util.*;

public class EmpresaMantenimiento {
    private final String  nombre;
    private final String codigo;

    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Tecnico> listaTecnicos = new ArrayList<>();
    private List<OrdenServicio> listaOrdenes = new LinkedList<>();
    private List<Repuesto> listaRepuestos = new ArrayList<>();
    private List<Servicio> listaServicios = new ArrayList<>();
    private Map<String,EquipoTecnologico> listaEquipos = new HashMap<>();

    public EmpresaMantenimiento(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
}
