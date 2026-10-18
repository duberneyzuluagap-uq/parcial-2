package co.edu.uniquindio.poo.model;

import java.util.Optional;

public class DetalleRepuesto {
    private int cantidadRepuesto;
    private double precioUnitarioAplicado;
    private Repuesto repuesto;

    public DetalleRepuesto(int cantidadRepuesto, double precioUnitarioAplicado, Repuesto repuesto) {
        this.cantidadRepuesto = cantidadRepuesto;
        this.precioUnitarioAplicado = precioUnitarioAplicado;
        this.repuesto = repuesto;
    }

    public int getCantidadRepuesto() {
        return cantidadRepuesto;
    }

    public void setCantidadRepuesto(int cantidadRepuesto) {
        this.cantidadRepuesto = cantidadRepuesto;
    }

    public double getPrecioUnitarioAplicado() {
        return precioUnitarioAplicado;
    }

    public void setPrecioUnitarioAplicado(double precioUnitarioAplicado) {
        this.precioUnitarioAplicado = precioUnitarioAplicado;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public double calcularSubTotal(){
        return  cantidadRepuesto*precioUnitarioAplicado;
    }


}
