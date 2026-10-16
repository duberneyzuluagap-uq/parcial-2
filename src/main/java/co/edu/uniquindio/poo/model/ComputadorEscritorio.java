package co.edu.uniquindio.poo.model;

public class ComputadorEscritorio extends EquipoTecnologico {
    private String tipoProcesador;
    private String memoriaRam;
    private boolean tieneTarjetaDedicada;

    public ComputadorEscritorio(String codigoRegistro, String numeroSerie, String marca, String modelo, int añoFabricacion, String estadoFisico,
                                String tipoProcesador,
                                String memoriaRam, boolean tieneTarjetaDedicada) {
        super(codigoRegistro, numeroSerie, marca, modelo, añoFabricacion, estadoFisico);
        this.tipoProcesador = tipoProcesador;
        this.memoriaRam = memoriaRam;
        this.tieneTarjetaDedicada = tieneTarjetaDedicada;
    }

    public String getTipoProcesador() {
        return tipoProcesador;
    }

    public void setTipoProcesador(String tipoProcesador) {
        this.tipoProcesador = tipoProcesador;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public boolean isTieneTarjetaDedicada() {
        return tieneTarjetaDedicada;
    }

    public void setTieneTarjetaDedicada(boolean tieneTarjetaDedicada) {
        this.tieneTarjetaDedicada = tieneTarjetaDedicada;
    }
}
