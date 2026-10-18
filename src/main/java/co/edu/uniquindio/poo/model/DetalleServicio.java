package co.edu.uniquindio.poo.model;

public class DetalleServicio {
    private String codigo;
    private String descripcionFalla;
    private EstadoOrden estadoIntervencion;
    private Tecnico tecnico;
    private EquipoTecnologico equipo;
    private Servicio servicio;
    private OrdenServicio ownedByOrdenServicio;

    public DetalleServicio(String codigo, String descripcionFalla, String estadoIntervencio, Tecnico tecnico,
                           EquipoTecnologico equipo, Servicio servicio, OrdenServicio ownedByOrdenServicio) {
        this.codigo = codigo;
        this.descripcionFalla = descripcionFalla;
        this.estadoIntervencion = estadoIntervencion;
        this.tecnico = tecnico;
        this.equipo = equipo;
        this.servicio = servicio;
        this.ownedByOrdenServicio = ownedByOrdenServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public EstadoOrden getEstadoIntervencion() {
        return estadoIntervencion;
    }

    public void setEstadoIntervencion(EstadoOrden estadoIntervencion) {
        this.estadoIntervencion = estadoIntervencion;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public String asignarTecnico(Tecnico tecnico) {
        if (tecnico.getEstadoLaboral() != EstadoLaboral.ACTIVO) {
            return "El tecnico no se encuentra activo";
        }

        boolean esCompatible = false;
        Especialdad especialidad = tecnico.getEspecialdad();

        if (especialidad == Especialdad.SOPORTE_GENERAL) {
            esCompatible = true;
        } else if (especialidad == Especialdad.COMPUTADORES &&
                (equipo instanceof ComputadorEscritorio || equipo instanceof ComputadorPortatil)) {
            esCompatible = true;
        } else if (especialidad == Especialdad.DISPOSITIVOS_MOVILES &&
                (equipo instanceof TelefonoInteligente)) {
            esCompatible = true;
        }

        if (esCompatible == false) {
            return "La especialidad del tecnico (" + especialidad + ") no coincide con el tipo de equipo";
        }


        this.tecnico = tecnico;
        return "Tecnico asignado correctamente al servicio";
    }

    public EquipoTecnologico getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTecnologico equipo) {
        this.equipo = equipo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public OrdenServicio getOwnedByOrdenServicio() {
        return ownedByOrdenServicio;
    }

    public void setOwnedByOrdenServicio(OrdenServicio ownedByOrdenServicio) {
        this.ownedByOrdenServicio = ownedByOrdenServicio;
    }

    public double calcularValorDetalle(){
        return servicio.calcularCostoServicio();
    }
}
