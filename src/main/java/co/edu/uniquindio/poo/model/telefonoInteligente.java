package co.edu.uniquindio.poo.model;

public class telefonoInteligente extends EquipoTecnologico {
    private String sistemaOperativo;
    private String capacidadAlmacenamiento;
    private String capacidadBateria;
    private boolean compatible5G;

    public telefonoInteligente(String codigoRegistro, String numeroSerie, String marca, String modelo,
                               int añoFabricacion, String estadoFisico, String sistemaOperativo,
                               String capacidadAlmacenamiento, String capacidadBateria, boolean compatible5G) {
        super(codigoRegistro, numeroSerie, marca, modelo, añoFabricacion, estadoFisico);
        this.sistemaOperativo = sistemaOperativo;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.capacidadBateria = capacidadBateria;
        this.compatible5G = compatible5G;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getCapacidadAlmacenamiento() {
        return capacidadAlmacenamiento;
    }

    public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
    }

    public String getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(String capacidadBateria) {
        this.capacidadBateria = capacidadBateria;
    }

    public boolean isCompatible5G() {
        return compatible5G;
    }

    public void setCompatible5G(boolean compatible5G) {
        this.compatible5G = compatible5G;
    }
}
