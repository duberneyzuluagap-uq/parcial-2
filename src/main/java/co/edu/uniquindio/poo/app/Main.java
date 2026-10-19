package co.edu.uniquindio.poo.app;
import co.edu.uniquindio.poo.model.*;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final EmpresaMantenimiento empresa = new EmpresaMantenimiento("TechRepair", "EMP-001");

    private static class OperacionCancelada extends RuntimeException {
    }

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = leerOpcion("MENU PRINCIPAL\n\n1. Clientes\n2. Tecnicos\n3. Repuestos\n4. Equipos tecnologicos\n5. Servicios\n6. Ordenes de servicio\n7. Consultas y reportes\n8. Cargar datos de prueba\n0. Salir");
            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuTecnicos();
                    break;
                case 3:
                    menuRepuestos();
                    break;
                case 4:
                    menuEquipos();
                    break;
                case 5:
                    menuServicios();
                    break;
                case 6:
                    menuOrdenes();
                    break;
                case 7:
                    menuConsultas();
                    break;
                case 8:
                    cargarDatosDePrueba();
                    break;
                case 0:
                    mostrar("Hasta luego");
                    break;
                default:
                    mostrar("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private static String leerTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(null, mensaje, "TechRepair", JOptionPane.QUESTION_MESSAGE);
        if (texto == null) {
            throw new OperacionCancelada();
        }
        return texto.trim();
    }

    private static int leerOpcion(String menu) {
        String texto = JOptionPane.showInputDialog(null, menu + "\n\nElija una opcion:", "TechRepair", JOptionPane.QUESTION_MESSAGE);
        if (texto == null) {
            return 0;
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                mostrar("Ingrese un numero entero valido");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                mostrar("Ingrese un numero valido");
            }
        }
    }

    private static int leerRango(String mensaje, int minimo, int maximo) {
        while (true) {
            int numero = leerEntero(mensaje);
            if (numero >= minimo && numero <= maximo) {
                return numero;
            }
            mostrar("Opcion invalida");
        }
    }

    private static boolean leerSiNo(String mensaje) {
        return leerRango(mensaje + "\n1. Si\n2. No", 1, 2) == 1;
    }

    private static <T extends Enum<T>> T elegirOpcion(String titulo, T[] valores) {
        String lista = titulo + "\n";
        for (int i = 0; i < valores.length; i++) {
            lista += (i + 1) + ". " + valores[i] + "\n";
        }
        return valores[leerRango(lista, 1, valores.length) - 1];
    }

    private static void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "TechRepair", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void menuClientes() {
        int opcion;
        do {
            opcion = leerOpcion("CLIENTES\n\n1. Registrar cliente\n2. Buscar cliente\n3. Actualizar cliente\n4. Eliminar cliente\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarCliente();
                        break;
                    case 2:
                        buscarCliente();
                        break;
                    case 3:
                        actualizarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarCliente() {
        String documento = leerTexto("Documento:");
        String nombre = leerTexto("Nombre completo:");
        String telefono = leerTexto("Telefono:");
        String correo = leerTexto("Correo electronico:");
        String direccion = leerTexto("Direccion de residencia:");
        mostrar(empresa.registrarCliente(new Cliente(documento, nombre, telefono, correo, direccion)));
    }

    private static void buscarCliente() {
        Optional<Cliente> clienteOptional = empresa.buscarCliente(leerTexto("Documento del cliente:"));
        if (clienteOptional.isPresent()) {
            Cliente c = clienteOptional.get();
            mostrar("CLIENTE\n\nDocumento: " + c.getDocumento()
                    + "\nNombre: " + c.getNombre()
                    + "\nTelefono: " + c.getTelefono()
                    + "\nCorreo: " + c.getCorreoElectronico()
                    + "\nDireccion: " + c.getDireccionResidencia()
                    + "\nEquipos registrados: " + c.getListaEquipos().size()
                    + "\nOrdenes registradas: " + c.getListaOrdenes().size());
        } else {
            mostrar("No se encontro el cliente");
        }
    }

    private static void actualizarCliente() {
        String documento = leerTexto("Documento del cliente a actualizar:");
        String nombre = leerTexto("Nuevo nombre completo:");
        String telefono = leerTexto("Nuevo telefono:");
        String correo = leerTexto("Nuevo correo electronico:");
        String direccion = leerTexto("Nueva direccion de residencia:");
        mostrar(empresa.actualizarCliente(documento, nombre, telefono, correo, direccion));
    }

    private static void eliminarCliente() {
        mostrar(empresa.eliminarCliente(leerTexto("Documento del cliente a eliminar:")));
    }

    private static void menuTecnicos() {
        int opcion;
        do {
            opcion = leerOpcion("TECNICOS\n\n1. Registrar tecnico\n2. Buscar tecnico\n3. Actualizar tecnico\n4. Eliminar tecnico\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarTecnico();
                        break;
                    case 2:
                        buscarTecnico();
                        break;
                    case 3:
                        actualizarTecnico();
                        break;
                    case 4:
                        eliminarTecnico();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarTecnico() {
        String documento = leerTexto("Documento:");
        String nombre = leerTexto("Nombre completo:");
        String codigo = leerTexto("Codigo del tecnico:");
        Especialdad especialidad = elegirOpcion("Especialidad:", Especialdad.values());
        EstadoLaboral estadoLaboral = elegirOpcion("Estado laboral:", EstadoLaboral.values());
        mostrar(empresa.registrarTecnico(new Tecnico(documento, nombre, codigo, especialidad, estadoLaboral)));
    }

    private static void buscarTecnico() {
        Optional<Tecnico> tecnicoOptional = empresa.buscarTecnico(leerTexto("Codigo del tecnico:"));
        if (tecnicoOptional.isPresent()) {
            Tecnico t = tecnicoOptional.get();
            mostrar("TECNICO\n\nCodigo: " + t.getCodigo()
                    + "\nDocumento: " + t.getDocumento()
                    + "\nNombre: " + t.getNombre()
                    + "\nEspecialidad: " + t.getEspecialdad()
                    + "\nEstado laboral: " + t.getEstadoLaboral()
                    + "\nDetalles asignados: " + t.getListaDetallesTecnico().size());
        } else {
            mostrar("No se encontro el tecnico");
        }
    }

    private static void actualizarTecnico() {
        String codigo = leerTexto("Codigo del tecnico a actualizar:");
        String nombre = leerTexto("Nuevo nombre completo:");
        Especialdad especialidad = elegirOpcion("Nueva especialidad:", Especialdad.values());
        EstadoLaboral estadoLaboral = elegirOpcion("Nuevo estado laboral:", EstadoLaboral.values());
        mostrar(empresa.actualizarTecnico(codigo, nombre, especialidad, estadoLaboral));
    }

    private static void eliminarTecnico() {
        mostrar(empresa.eliminarTecnico(leerTexto("Codigo del tecnico a eliminar:")));
    }

    private static void menuRepuestos() {
        int opcion;
        do {
            opcion = leerOpcion("REPUESTOS\n\n1. Registrar repuesto\n2. Buscar repuesto\n3. Actualizar repuesto\n4. Eliminar repuesto\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarRepuesto();
                        break;
                    case 2:
                        buscarRepuesto();
                        break;
                    case 3:
                        actualizarRepuesto();
                        break;
                    case 4:
                        eliminarRepuesto();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarRepuesto() {
        String codigo = leerTexto("Codigo del repuesto:");
        String nombre = leerTexto("Nombre:");
        String descripcion = leerTexto("Descripcion:");
        double precio = leerDecimal("Precio unitario:");
        int cantidad = leerEntero("Cantidad disponible:");
        String categoria = leerTexto("Categoria de compatibilidad:");
        mostrar(empresa.registrarRepuesto(new Repuesto(codigo, nombre, descripcion, precio, cantidad, categoria)));
    }

    private static void buscarRepuesto() {
        Optional<Repuesto> repuestoOptional = empresa.buscarRepuesto(leerTexto("Codigo del repuesto:"));
        if (repuestoOptional.isPresent()) {
            Repuesto r = repuestoOptional.get();
            mostrar("REPUESTO\n\nCodigo: " + r.getCodigo()
                    + "\nNombre: " + r.getNombre()
                    + "\nDescripcion: " + r.getDescripcion()
                    + "\nPrecio unitario: " + r.getPrecioUnitario()
                    + "\nCantidad disponible: " + r.getCantidadDisponible()
                    + "\nCategoria de compatibilidad: " + r.getCategoriaCompatibilidad());
        } else {
            mostrar("No se encontro el repuesto");
        }
    }

    private static void actualizarRepuesto() {
        String codigo = leerTexto("Codigo del repuesto a actualizar:");
        String nombre = leerTexto("Nuevo nombre:");
        String descripcion = leerTexto("Nueva descripcion:");
        double precio = leerDecimal("Nuevo precio unitario:");
        int cantidad = leerEntero("Nueva cantidad disponible:");
        String categoria = leerTexto("Nueva categoria de compatibilidad:");
        mostrar(empresa.actualizarRepuesto(codigo, nombre, descripcion, precio, cantidad, categoria));
    }

    private static void eliminarRepuesto() {
        mostrar(empresa.eliminarRepuesto(leerTexto("Codigo del repuesto a eliminar:")));
    }

    private static void menuEquipos() {
        int opcion;
        do {
            opcion = leerOpcion("EQUIPOS TECNOLOGICOS\n\n1. Registrar equipo\n2. Buscar equipo\n3. Actualizar equipo\n4. Eliminar equipo\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarEquipo();
                        break;
                    case 2:
                        buscarEquipo();
                        break;
                    case 3:
                        actualizarEquipo();
                        break;
                    case 4:
                        eliminarEquipo();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarEquipo() {
        int tipo = leerRango("Tipo de equipo:\n1. Computador portatil\n2. Computador de escritorio\n3. Telefono inteligente", 1, 3);
        String codigo = leerTexto("Codigo de registro:");
        String serie = leerTexto("Numero de serie:");
        String marca = leerTexto("Marca:");
        String modelo = leerTexto("Modelo:");
        int anio = leerEntero("Año de fabricacion:");
        String estadoFisico = leerTexto("Estado fisico:");
        EquipoTecnologico equipo;
        if (tipo == 1) {
            String pulgadas = leerTexto("Tamaño en pulgadas:");
            String ram = leerTexto("Memoria RAM:");
            String almacenamiento = leerTexto("Capacidad de almacenamiento:");
            boolean bateriaExtraible = leerSiNo("¿Tiene bateria extraible?");
            equipo = new ComputadorPortatil(codigo, serie, marca, modelo, anio, estadoFisico, pulgadas, ram, almacenamiento, bateriaExtraible);
        } else if (tipo == 2) {
            String procesador = leerTexto("Tipo de procesador:");
            String ram = leerTexto("Memoria RAM:");
            boolean tarjetaDedicada = leerSiNo("¿Tiene tarjeta dedicada?");
            equipo = new ComputadorEscritorio(codigo, serie, marca, modelo, anio, estadoFisico, procesador, ram, tarjetaDedicada);
        } else {
            String sistemaOperativo = leerTexto("Sistema operativo:");
            String almacenamiento = leerTexto("Capacidad de almacenamiento:");
            String bateria = leerTexto("Capacidad de bateria:");
            boolean compatible5G = leerSiNo("¿Es compatible con 5G?");
            equipo = new TelefonoInteligente(codigo, serie, marca, modelo, anio, estadoFisico, sistemaOperativo, almacenamiento, bateria, compatible5G);
        }
        String documentoCliente = leerTexto("Documento del cliente dueño del equipo:");
        mostrar(empresa.registrarEquipo(equipo, documentoCliente));
    }

    private static void buscarEquipo() {
        Optional<EquipoTecnologico> equipoOptional = empresa.buscarEquipo(leerTexto("Codigo de registro del equipo:"));
        if (equipoOptional.isPresent()) {
            EquipoTecnologico e = equipoOptional.get();
            String tipo = "Telefono inteligente";
            if (e instanceof ComputadorPortatil) {
                tipo = "Computador portatil";
            } else if (e instanceof ComputadorEscritorio) {
                tipo = "Computador de escritorio";
            }
            mostrar("EQUIPO\n\nTipo: " + tipo
                    + "\nCodigo de registro: " + e.getCodigoRegistro()
                    + "\nNumero de serie: " + e.getNumeroSerie()
                    + "\nMarca: " + e.getMarca()
                    + "\nModelo: " + e.getModelo()
                    + "\nAño de fabricacion: " + e.getAñoFabricacion()
                    + "\nEstado fisico: " + e.getEstadoFisico());
        } else {
            mostrar("No se encontro el equipo");
        }
    }

    private static void actualizarEquipo() {
        String codigo = leerTexto("Codigo de registro del equipo a actualizar:");
        String marca = leerTexto("Nueva marca:");
        String modelo = leerTexto("Nuevo modelo:");
        int anio = leerEntero("Nuevo año de fabricacion:");
        String estadoFisico = leerTexto("Nuevo estado fisico:");
        mostrar(empresa.actualizarEquipo(codigo, marca, modelo, anio, estadoFisico));
    }

    private static void eliminarEquipo() {
        mostrar(empresa.eliminarEquipo(leerTexto("Codigo de registro del equipo a eliminar:")));
    }

    private static void menuServicios() {
        int opcion;
        do {
            opcion = leerOpcion("SERVICIOS\n\n1. Registrar servicio\n2. Buscar servicio\n3. Actualizar servicio\n4. Eliminar servicio\n5. Agregar repuesto a una reparacion correctiva\n6. Calcular costo de un servicio\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarServicio();
                        break;
                    case 2:
                        buscarServicio();
                        break;
                    case 3:
                        actualizarServicio();
                        break;
                    case 4:
                        eliminarServicio();
                        break;
                    case 5:
                        agregarRepuestoAReparacion();
                        break;
                    case 6:
                        calcularCostoServicio();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarServicio() {
        int tipo = leerRango("Tipo de servicio:\n1. Diagnostico\n2. Mantenimiento preventivo\n3. Reparacion correctiva", 1, 3);
        EquipoTecnologico equipo = null;
        if (tipo == 2) {
            Optional<EquipoTecnologico> equipoOptional = empresa.buscarEquipo(leerTexto("Codigo de registro del equipo:"));
            if (equipoOptional.isEmpty()) {
                mostrar("No se encontro el equipo");
                return;
            }
            equipo = equipoOptional.get();
        }
        String codigo = leerTexto("Codigo del servicio:");
        String nombre = leerTexto("Nombre:");
        String descripcion = leerTexto("Descripcion:");
        double tarifaBase = leerDecimal("Tarifa base:");
        Servicio servicio;
        if (tipo == 1) {
            servicio = new Diagnostico(codigo, nombre, descripcion, tarifaBase);
        } else if (tipo == 2) {
            servicio = new MantenimientoPreventivo(codigo, nombre, descripcion, tarifaBase, equipo);
        } else {
            int horas = leerEntero("Horas trabajadas:");
            servicio = new ReparacionCorrectiva(codigo, nombre, descripcion, tarifaBase, horas);
        }
        mostrar(empresa.registrarServicio(servicio));
    }

    private static void buscarServicio() {
        Optional<Servicio> servicioOptional = empresa.buscarServicio(leerTexto("Codigo del servicio:"));
        if (servicioOptional.isPresent()) {
            Servicio s = servicioOptional.get();
            String tipo = "Reparacion correctiva";
            if (s instanceof Diagnostico) {
                tipo = "Diagnostico";
            } else if (s instanceof MantenimientoPreventivo) {
                tipo = "Mantenimiento preventivo";
            }
            mostrar("SERVICIO\n\nTipo: " + tipo
                    + "\nCodigo: " + s.getCodigo()
                    + "\nNombre: " + s.getNombre()
                    + "\nDescripcion: " + s.getDescripcion()
                    + "\nTarifa base: " + s.getTarifaBase()
                    + "\nCosto calculado: " + s.calcularCostoServicio());
        } else {
            mostrar("No se encontro el servicio");
        }
    }

    private static void actualizarServicio() {
        String codigo = leerTexto("Codigo del servicio a actualizar:");
        String nombre = leerTexto("Nuevo nombre:");
        String descripcion = leerTexto("Nueva descripcion:");
        double tarifaBase = leerDecimal("Nueva tarifa base:");
        mostrar(empresa.actualizarServicio(codigo, nombre, descripcion, tarifaBase));
    }

    private static void eliminarServicio() {
        mostrar(empresa.eliminarServicio(leerTexto("Codigo del servicio a eliminar:")));
    }

    private static void agregarRepuestoAReparacion() {
        Optional<Servicio> servicioOptional = empresa.buscarServicio(leerTexto("Codigo de la reparacion correctiva:"));
        if (servicioOptional.isEmpty()) {
            mostrar("No se encontro el servicio");
            return;
        }
        if (!(servicioOptional.get() instanceof ReparacionCorrectiva)) {
            mostrar("El servicio indicado no es una reparacion correctiva");
            return;
        }
        ReparacionCorrectiva reparacion = (ReparacionCorrectiva) servicioOptional.get();
        Optional<Repuesto> repuestoOptional = empresa.buscarRepuesto(leerTexto("Codigo del repuesto:"));
        if (repuestoOptional.isEmpty()) {
            mostrar("No se encontro el repuesto");
            return;
        }
        Repuesto repuesto = repuestoOptional.get();
        int cantidad = leerEntero("Cantidad de unidades:");
        if (cantidad <= 0) {
            mostrar("La cantidad debe ser mayor a 0");
            return;
        }
        DetalleRepuesto detalle = new DetalleRepuesto(cantidad, repuesto.getPrecioUnitario(), repuesto);
        mostrar(reparacion.registrarDetalleRepuesto(detalle));
    }

    private static void calcularCostoServicio() {
        Optional<Servicio> servicioOptional = empresa.buscarServicio(leerTexto("Codigo del servicio:"));
        if (servicioOptional.isPresent()) {
            mostrar("Costo del servicio " + servicioOptional.get().getNombre() + ": " + servicioOptional.get().calcularCostoServicio());
        } else {
            mostrar("No se encontro el servicio");
        }
    }

    private static void menuOrdenes() {
        int opcion;
        do {
            opcion = leerOpcion("ORDENES DE SERVICIO\n\n1. Registrar orden\n2. Buscar orden\n3. Actualizar orden\n4. Eliminar orden\n5. Agregar detalle de servicio a una orden\n6. Asignar tecnico a un detalle\n7. Actualizar estado de un detalle\n8. Eliminar detalle de una orden\n9. Calcular valor total de una orden\n10. Finalizar orden\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        registrarOrden();
                        break;
                    case 2:
                        buscarOrden();
                        break;
                    case 3:
                        actualizarOrden();
                        break;
                    case 4:
                        eliminarOrden();
                        break;
                    case 5:
                        agregarDetalle();
                        break;
                    case 6:
                        asignarTecnico();
                        break;
                    case 7:
                        actualizarEstadoDetalle();
                        break;
                    case 8:
                        eliminarDetalle();
                        break;
                    case 9:
                        calcularValorOrden();
                        break;
                    case 10:
                        finalizarOrden();
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void registrarOrden() {
        String codigo = leerTexto("Codigo de la orden:");
        String observaciones = leerTexto("Observaciones:");
        String documentoCliente = leerTexto("Documento del cliente:");
        OrdenServicio orden = new OrdenServicio(codigo, LocalDate.now(), observaciones, EstadoOrden.ABIERTA);
        mostrar(empresa.registrarOrdenServicio(orden, documentoCliente));
    }

    private static void buscarOrden() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isPresent()) {
            OrdenServicio o = ordenOptional.get();
            String resultado = "ORDEN\n\nCodigo: " + o.getCodigo()
                    + "\nFecha: " + o.getFecha()
                    + "\nEstado: " + o.getEstadoOrden()
                    + "\nObservaciones: " + o.getObservaciones()
                    + "\nValor total: " + o.calcularValorTotalOrden()
                    + "\n\nDetalles:\n";
            if (o.getListaDetalles().isEmpty()) {
                resultado += "Sin detalles registrados\n";
            }
            for (DetalleServicio d : o.getListaDetalles()) {
                String tecnico = "Sin tecnico";
                if (d.getTecnico() != null) {
                    tecnico = d.getTecnico().getNombre();
                }
                resultado += d.getCodigo() + " - " + d.getDescripcionFalla() + " - " + d.getEstadoIntervencion() + " - " + tecnico + "\n";
            }
            mostrar(resultado);
        } else {
            mostrar("No se encontro la orden");
        }
    }

    private static void actualizarOrden() {
        String codigo = leerTexto("Codigo de la orden a actualizar:");
        EstadoOrden estado = elegirOpcion("Nuevo estado de la orden:", EstadoOrden.values());
        String observaciones = leerTexto("Nuevas observaciones:");
        mostrar(empresa.actualizarOrdenServicio(codigo, estado, observaciones));
    }

    private static void eliminarOrden() {
        mostrar(empresa.eliminarOrdenServicio(leerTexto("Codigo de la orden a eliminar:")));
    }

    private static void agregarDetalle() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isEmpty()) {
            mostrar("No se encontro la orden");
            return;
        }
        OrdenServicio orden = ordenOptional.get();
        Optional<EquipoTecnologico> equipoOptional = empresa.buscarEquipo(leerTexto("Codigo de registro del equipo:"));
        if (equipoOptional.isEmpty()) {
            mostrar("No se encontro el equipo");
            return;
        }
        Optional<Servicio> servicioOptional = empresa.buscarServicio(leerTexto("Codigo del servicio:"));
        if (servicioOptional.isEmpty()) {
            mostrar("No se encontro el servicio");
            return;
        }
        String codigo = leerTexto("Codigo del detalle:");
        String descripcionFalla = leerTexto("Descripcion de la falla:");
        DetalleServicio detalle = new DetalleServicio(codigo, descripcionFalla, EstadoOrden.ABIERTA, null,
                equipoOptional.get(), servicioOptional.get(), orden);
        mostrar(orden.registrarDetalleServicio(detalle));
    }

    private static void asignarTecnico() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isEmpty()) {
            mostrar("No se encontro la orden");
            return;
        }
        Optional<DetalleServicio> detalleOptional = ordenOptional.get().buscarDetalleServicio(leerTexto("Codigo del detalle:"));
        if (detalleOptional.isEmpty()) {
            mostrar("No se encontro el detalle en la orden");
            return;
        }
        Optional<Tecnico> tecnicoOptional = empresa.buscarTecnico(leerTexto("Codigo del tecnico:"));
        if (tecnicoOptional.isEmpty()) {
            mostrar("No se encontro el tecnico");
            return;
        }
        mostrar(detalleOptional.get().asignarTecnico(tecnicoOptional.get()));
    }

    private static void actualizarEstadoDetalle() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isEmpty()) {
            mostrar("No se encontro la orden");
            return;
        }
        String codigoDetalle = leerTexto("Codigo del detalle:");
        EstadoOrden estado = elegirOpcion("Nuevo estado del detalle:", EstadoOrden.values());
        mostrar(ordenOptional.get().actualizarEstadoDetalle(codigoDetalle, estado));
    }

    private static void eliminarDetalle() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isEmpty()) {
            mostrar("No se encontro la orden");
            return;
        }
        mostrar(ordenOptional.get().eliminarDetalleServicio(leerTexto("Codigo del detalle a eliminar:")));
    }

    private static void calcularValorOrden() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isPresent()) {
            mostrar("Valor total de la orden " + ordenOptional.get().getCodigo() + ": " + ordenOptional.get().calcularValorTotalOrden());
        } else {
            mostrar("No se encontro la orden");
        }
    }

    private static void finalizarOrden() {
        Optional<OrdenServicio> ordenOptional = empresa.buscarOrdenServicio(leerTexto("Codigo de la orden:"));
        if (ordenOptional.isPresent()) {
            mostrar(ordenOptional.get().finalizarOrden());
        } else {
            mostrar("No se encontro la orden");
        }
    }

    private static void menuConsultas() {
        int opcion;
        do {
            opcion = leerOpcion("CONSULTAS Y REPORTES\n\n1. Ordenes de un cliente\n2. Trabajos de un tecnico\n3. Equipos sin finalizar\n4. Repuestos criticos\n5. Reporte de servicios\n6. Reporte de tecnicos\n0. Volver");
            try {
                switch (opcion) {
                    case 1:
                        consultarOrdenesCliente();
                        break;
                    case 2:
                        consultarTrabajosTecnico();
                        break;
                    case 3:
                        consultarEquiposSinFinalizar();
                        break;
                    case 4:
                        consultarRepuestosCriticos();
                        break;
                    case 5:
                        mostrar(empresa.generarReporteServicios());
                        break;
                    case 6:
                        mostrar(empresa.generarReporteTecnicos());
                        break;
                    case 0:
                        break;
                    default:
                        mostrar("Opcion invalida");
                }
            } catch (OperacionCancelada e) {
                mostrar("Operacion cancelada");
            }
        } while (opcion != 0);
    }

    private static void consultarOrdenesCliente() {
        Map<String, OrdenServicio> ordenes = empresa.consultarOrdenesCliente(leerTexto("Documento del cliente:"));
        if (ordenes.isEmpty()) {
            mostrar("El cliente no existe o no tiene ordenes registradas");
            return;
        }
        String resultado = "ORDENES DEL CLIENTE\n\n";
        for (Map.Entry<String, OrdenServicio> entrada : ordenes.entrySet()) {
            OrdenServicio o = entrada.getValue();
            resultado += o.getCodigo() + " - " + o.getFecha() + " - " + o.getEstadoOrden() + " - " + o.getObservaciones() + "\n";
        }
        mostrar(resultado);
    }

    private static void consultarTrabajosTecnico() {
        Map<DetalleServicio, EstadoOrden> trabajos = empresa.consultarTrabajosTecnico(leerTexto("Codigo del tecnico:"));
        if (trabajos.isEmpty()) {
            mostrar("El tecnico no existe o no tiene trabajos asignados");
            return;
        }
        String resultado = "TRABAJOS DEL TECNICO\n\n";
        for (Map.Entry<DetalleServicio, EstadoOrden> entrada : trabajos.entrySet()) {
            resultado += entrada.getKey().getCodigo() + " - " + entrada.getKey().getDescripcionFalla() + " - " + entrada.getValue() + "\n";
        }
        mostrar(resultado);
    }

    private static void consultarEquiposSinFinalizar() {
        List<EquipoTecnologico> equipos = empresa.consultarEquiposSinFinalizar();
        if (equipos.isEmpty()) {
            mostrar("No hay equipos con intervenciones pendientes");
            return;
        }
        String resultado = "EQUIPOS SIN FINALIZAR\n\n";
        for (EquipoTecnologico e : equipos) {
            resultado += e.getCodigoRegistro() + " - " + e.getMarca() + " " + e.getModelo() + "\n";
        }
        mostrar(resultado);
    }

    private static void consultarRepuestosCriticos() {
        List<Repuesto> repuestos = empresa.identificarRepuestosCriticos();
        if (repuestos.isEmpty()) {
            mostrar("No hay repuestos en estado critico");
            return;
        }
        String resultado = "REPUESTOS CRITICOS\n\n";
        for (Repuesto r : repuestos) {
            resultado += r.getCodigo() + " - " + r.getNombre() + " - " + r.getCantidadDisponible() + " unidades\n";
        }
        mostrar(resultado);
    }

    private static void cargarDatosDePrueba() {
        String resultado = "DATOS DE PRUEBA\n\n";

        Cliente ana = new Cliente("1001", "Ana Gómez", "3001234567", "ana@mail.com", "Calle 10 #5-20");
        Cliente luis = new Cliente("1002", "Luis Pérez", "3109876543", "luis@mail.com", "Carrera 14 #8-15");
        resultado += empresa.registrarCliente(ana) + "\n";
        resultado += empresa.registrarCliente(luis) + "\n";

        resultado += empresa.registrarTecnico(new Tecnico("2001", "Carlos Ruiz", "T-01", Especialdad.COMPUTADORES, EstadoLaboral.ACTIVO)) + "\n";
        resultado += empresa.registrarTecnico(new Tecnico("2002", "María Torres", "T-02", Especialdad.DISPOSITIVOS_MOVILES, EstadoLaboral.ACTIVO)) + "\n";
        resultado += empresa.registrarTecnico(new Tecnico("2003", "Pedro Ríos", "T-03", Especialdad.SOPORTE_GENERAL, EstadoLaboral.INACTIVO)) + "\n";

        resultado += empresa.registrarRepuesto(new Repuesto("R-01", "Pantalla 15.6", "Pantalla LED", 120_000, 10, "Portatil")) + "\n";
        resultado += empresa.registrarRepuesto(new Repuesto("R-02", "Bateria", "Bateria de litio", 80_000, 4, "Telefono")) + "\n";
        resultado += empresa.registrarRepuesto(new Repuesto("R-03", "Disco SSD 512GB", "Disco de estado solido", 200_000, 3, "Computador")) + "\n";

        ComputadorPortatil portatil = new ComputadorPortatil("EQ-01", "SN-111", "Lenovo", "ThinkPad", 2022, "Bueno", "15.6", "16GB", "512GB", true);
        ComputadorEscritorio escritorio = new ComputadorEscritorio("EQ-02", "SN-222", "HP", "Pavilion", 2021, "Regular", "Intel i7", "32GB", true);
        TelefonoInteligente telefono = new TelefonoInteligente("EQ-03", "SN-333", "Samsung", "Galaxy S23", 2023, "Bueno", "Android", "256GB", "4000mAh", true);
        resultado += empresa.registrarEquipo(portatil, "1001") + "\n";
        resultado += empresa.registrarEquipo(escritorio, "1001") + "\n";
        resultado += empresa.registrarEquipo(telefono, "1001") + "\n";

        resultado += empresa.registrarServicio(new Diagnostico("S-01", "Diagnostico general", "Revision completa", 35_000)) + "\n";
        resultado += empresa.registrarServicio(new MantenimientoPreventivo("S-02", "Mantenimiento portatil", "Limpieza", 50_000, portatil)) + "\n";
        resultado += empresa.registrarServicio(new ReparacionCorrectiva("S-03", "Cambio de pantalla", "Reemplazo de pantalla", 0, 3)) + "\n";
        resultado += empresa.registrarServicio(new MantenimientoPreventivo("S-04", "Mantenimiento telefono", "Revision", 30_000, telefono)) + "\n";
        resultado += empresa.registrarServicio(new MantenimientoPreventivo("S-05", "Mantenimiento escritorio", "Limpieza", 55_000, escritorio)) + "\n";

        mostrar(resultado);
    }
}


