package model;

public class Ruta {
    private String idOrigen;
    private String idDestino;
    private int tiempo;
    private boolean activa;

    public Ruta(String idOrigen, String idDestino, int tiempo) {
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.tiempo = tiempo;
        this.activa = true;
    }

    public String getIdOrigen() {
        return idOrigen;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public int getTiempo() {
        return tiempo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Ruta [" + idOrigen + " <-> " + idDestino + " | " + tiempo + " min | Activa: " + activa + "]";
    }
}