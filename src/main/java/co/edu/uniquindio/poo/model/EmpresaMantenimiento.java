package co.edu.uniquindio.poo.model;

import java.util.*;

/**
 * Esta clase representa
 * @author : Duberney Zuluaga Pamplona-Dilan  Burbano
 * @Version : 1
 * @Fecha : 15/10/2026
 */
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

    public Optional<Cliente> buscarCliente(String documento) {
        Optional<Cliente> clienteOptional = listaClientes.stream()
                .filter(c -> c.getDocumento().equalsIgnoreCase(documento))
                .findFirst();
        return clienteOptional;
    }
        // CRUD CLIENTE
    public String registrarCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = buscarCliente(cliente.getDocumento());
        if (clienteOptional.isPresent()) {
            return "Error el cliente con documento " + cliente.getDocumento() + " ya existe en el sistema";
        }
        listaClientes.add(cliente);
        return "Registrado con éxito";
    }

    public String actualizarCliente(String documento, String nuevoNombre, String nuevoTelefono, String nuevoCorreo, String nuevaDireccion) {
        Optional<Cliente> clienteOptional = buscarCliente(documento);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNombre(nuevoNombre);
            cliente.setTelefono(nuevoTelefono);
            cliente.setCorreoElectronico(nuevoCorreo);
            cliente.setDireccionResidencia(nuevaDireccion);
            return "Actualizado con éxito";
        }
        return "Error: No se encontró ningún cliente con el documento " + documento ;
    }

    public String eliminarCliente(String documento) {
        Optional<Cliente> clienteOptional = buscarCliente(documento);
        if (clienteOptional.isPresent()) {
            listaClientes.remove(clienteOptional.get());
            return "Eliminado con éxito";
        }
        return "Error: No se encontró ningún cliente con el documento " + documento ;
    }
    // CRUD TECNICO
    public Optional<Tecnico> buscarTecnico(String codigoTecnico) {
        Optional<Tecnico> tecnicoOptional = listaTecnicos.stream()
                .filter(t -> t.getCodigo().equalsIgnoreCase(codigoTecnico))
                .findFirst();
        return tecnicoOptional;
    }

    public String registrarTecnico(Tecnico tecnico) {
        Optional<Tecnico> tecnicoOptional = buscarTecnico(tecnico.getCodigo());
        if (tecnicoOptional.isPresent()) {
            return "El tecnico con codigo " + tecnico.getCodigo() + " ya existe en el sistema";
        }
        listaTecnicos.add(tecnico);
        return "Registrado con exito";
    }

    public String actualizarTecnico(String codigoTecnico, String nuevoNombre, Especialdad nuevaEspecialidad, EstadoLaboral nuevoEstado) {
        Optional<Tecnico> tecnicoOptional = buscarTecnico(codigoTecnico);
        if (tecnicoOptional.isPresent()) {
            Tecnico tecnico = tecnicoOptional.get();
            tecnico.setNombre(nuevoNombre);
            tecnico.setEspecialdad(nuevaEspecialidad);
            tecnico.setEstadoLaboral(nuevoEstado);
            return "Actualizado con exito";
        }
        return "No se encontro ningun tecnico con el codigo " + codigoTecnico;
    }

    public String eliminarTecnico(String codigoTecnico) {
        Optional<Tecnico> tecnicoOptional = buscarTecnico(codigoTecnico);
        if (tecnicoOptional.isPresent()) {
            listaTecnicos.remove(tecnicoOptional.get());
            return "Eliminado con exito";
        }
        return "No se encontro ningun tecnico con el codigo " + codigoTecnico;
    }

    // CRUD ORDENES
    public Optional<OrdenServicio> buscarOrdenServicio(String codigoOrden) {
        Optional<OrdenServicio> ordenServicioOptional = listaOrdenes.stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigoOrden))
                .findFirst();
        return ordenServicioOptional;
    }

    public String registrarOrdenServicio(OrdenServicio ordenServicio) {
        Optional<OrdenServicio> ordenServicioOptional = buscarOrdenServicio(ordenServicio.getCodigo());
        if (ordenServicioOptional.isPresent()) {
            return "La orden de servicio " + ordenServicio.getCodigo() + " ya esta registrada";
        }
        listaOrdenes.add(ordenServicio);
        return "Orden de servicio guardada correctamente";
    }

    public String actualizarOrdenServicio(String codigoOrden, EstadoOrden nuevoEstado, String nuevasObservaciones) {
        Optional<OrdenServicio> ordenServicioOptional = buscarOrdenServicio(codigoOrden);
        if (ordenServicioOptional.isPresent()) {
            OrdenServicio ordenServicio = ordenServicioOptional.get();
            ordenServicio.setEstadoOrden(nuevoEstado);
            ordenServicio.setObservaciones(nuevasObservaciones);
            return "Datos de la orden modificados correctamente";
        }
        return "La orden " + codigoOrden + " no se encuentra en la lista";
    }

    public String eliminarOrdenServicio(String codigoOrden) {
        Optional<OrdenServicio> ordenServicioOptional = buscarOrdenServicio(codigoOrden);
        if (ordenServicioOptional.isPresent()) {
            listaOrdenes.remove(ordenServicioOptional.get());
            return "Orden eliminada del sistema";
        }
        return "No fue posible encontrar la orden " + codigoOrden;
    }
    // CRUD REPUESTOS
    public Optional<Repuesto> buscarRepuesto(String codigo) {
        Optional<Repuesto> repuestoOptional = listaRepuestos.stream()
                .filter(r -> r.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
        return repuestoOptional;
    }

    public String registrarRepuesto(Repuesto repuesto) {
        Optional<Repuesto> repuestoOptional = buscarRepuesto(repuesto.getCodigo());
        if (repuestoOptional.isPresent()) {
            return "El repuesto " + repuesto.getCodigo() + " ya existe en el inventario";
        }
        listaRepuestos.add(repuesto);
        return "Repuesto ingresado exitosamente";
    }

    public String actualizarRepuesto(String codigo, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevaCantidad, String nuevaCompatibilidad) {
        Optional<Repuesto> repuestoOptional = buscarRepuesto(codigo);
        if (repuestoOptional.isPresent()) {
            Repuesto repuesto = repuestoOptional.get();
            repuesto.setNombre(nuevoNombre);
            repuesto.setDescripcion(nuevaDescripcion);
            repuesto.setPrecioUnitario(nuevoPrecio);
            repuesto.setCantidadDisponible(nuevaCantidad);
            repuesto.setCategoriaCompatibilidad(nuevaCompatibilidad);
            return "Informacion del repuesto actualizada";
        }
        return "No existe un repuesto con el codigo " + codigo;
    }

    public String eliminarRepuesto(String codigo) {
        Optional<Repuesto> repuestoOptional = buscarRepuesto(codigo);
        if (repuestoOptional.isPresent()) {
            listaRepuestos.remove(repuestoOptional.get());
            return "Repuesto retirado del inventario";
        }
        return "No se pudo retirar el repuesto " + codigo + " porque no existe";
    }
        //CRUD SERVICIO
        public Optional<Servicio> buscarServicio(String codigoServicio) {
            Optional<Servicio> servicioOptional = listaServicios.stream()
                    .filter(s -> s.getCodigo().equalsIgnoreCase(codigoServicio))
                    .findFirst();
            return servicioOptional;
        }

    public String registrarServicio(Servicio servicio) {
        Optional<Servicio> servicioOptional = buscarServicio(servicio.getCodigo());
        if (servicioOptional.isPresent()) {
            return "El servicio " + servicio.getCodigo() + " ya esta registrado en la lista";
        }
        listaServicios.add(servicio);
        return "Servicio agregado a la lista con exito";
    }

    public String actualizarServicio(String codigoServicio, String nuevoNombre, String nuevaDescripcion, double nuevaTarifaBase) {
        Optional<Servicio> servicioOptional = buscarServicio(codigoServicio);
        if (servicioOptional.isPresent()) {
            Servicio servicio = servicioOptional.get();
            servicio.setNombre(nuevoNombre);
            servicio.setDescripcion(nuevaDescripcion);
            servicio.setTarifaBase(nuevaTarifaBase);
            return "Servicio del catalogo actualizado correctamente";
        }
        return "El servicio " + codigoServicio + " no existe en la lista";
    }

    public String eliminarServicio(String codigoServicio) {
        Optional<Servicio> servicioOptional = buscarServicio(codigoServicio);
        if (servicioOptional.isPresent()) {
            listaServicios.remove(servicioOptional.get());
            return "Servicio eliminado del catalogo";
        }
        return "No se pudo eliminar el servicio " + codigoServicio + " porque no esta en la lista";
    }
        //CRUD EQUIPOS TECNOLOGICOS
        public Optional<EquipoTecnologico> buscarEquipo(String codigoRegistro) {
            Optional<EquipoTecnologico> equipoOptional = listaEquipos.values().stream()
                    .filter(e -> e.getCodigoRegistro().equalsIgnoreCase(codigoRegistro))
                    .findFirst();
            return equipoOptional;
        }

    public String registrarEquipo(EquipoTecnologico equipo, String documentoCliente) {
        Optional<EquipoTecnologico> equipoOptional = buscarEquipo(equipo.getCodigoRegistro());
        if (equipoOptional.isPresent()) {
            return "El equipo " + equipo.getCodigoRegistro() + " ya se encuentra registrado";
        }

        Optional<Cliente> clienteOptional = buscarCliente(documentoCliente);
        if (clienteOptional.isPresent()) {
            listaEquipos.put(equipo.getCodigoRegistro(), equipo);
            clienteOptional.get().getListaEquipos().add(equipo);
            return "Equipo registrado y vinculado al cliente";
        }

        return "No fue posible asociar el equipo porque el cliente " + documentoCliente + " no existe";
    }

    public String actualizarEquipo(String codigoRegistro, String nuevaMarca, String nuevoModelo, int nuevoAño, String nuevoEstadoFisico) {
        Optional<EquipoTecnologico> equipoOptional = buscarEquipo(codigoRegistro);
        if (equipoOptional.isPresent()) {
            EquipoTecnologico equipo = equipoOptional.get();
            equipo.setMarca(nuevaMarca);
            equipo.setModelo(nuevoModelo);
            equipo.setAñoFabricacion(nuevoAño);
            equipo.setEstadoFisico(nuevoEstadoFisico);
            return "Datos del equipo actualizados con exito";
        }
        return "El equipo con codigo " + codigoRegistro + " no esta en la base de datos";
    }

    public String eliminarEquipo(String codigoRegistro) {
        Optional<EquipoTecnologico> equipoOptional = buscarEquipo(codigoRegistro);
        if (equipoOptional.isPresent()) {
            listaEquipos.remove(codigoRegistro);
            return "Equipo eliminado del sistema";
        }
        return "No se pudo remover el equipo " + codigoRegistro + " porque no existe";
    }
}
