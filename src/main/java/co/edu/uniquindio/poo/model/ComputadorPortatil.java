package co.edu.uniquindio.poo.model;

public class ComputadorPortatil extends EquipoTecnologico{
    private  String taminioPulgadas;
    private String memoriaRam;
    private String capacidadAlmacenamiento;
    private boolean bateriaExtraible;

    public ComputadorPortatil(String codigoRegistro, String numeroSerie, String marca, String modelo, int añoFabricacion, String estadoFisico, String taminioPulgadas,
                              String memoriaRam, String capacidadAlmacenamiento, boolean bateriaExtraible) {
        super(codigoRegistro, numeroSerie, marca, modelo, añoFabricacion, estadoFisico);
        this.taminioPulgadas = taminioPulgadas;
        this.memoriaRam = memoriaRam;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.bateriaExtraible = bateriaExtraible;
    }

    public String getTaminioPulgadas() {
        return taminioPulgadas;
    }

    public void setTaminioPulgadas(String taminioPulgadas) {
        this.taminioPulgadas = taminioPulgadas;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getCapacidadAlmacenamiento() {
        return capacidadAlmacenamiento;
    }

    public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
    }

    public boolean isBateriaExtraible() {
        return bateriaExtraible;
    }

    public void setBateriaExtraible(boolean bateriaExtraible) {
        this.bateriaExtraible = bateriaExtraible;
    }
}
