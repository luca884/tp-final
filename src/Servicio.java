public abstract class Servicio {

    private static int contIdServicio = 0;
    private int idServicio;
    private double precio;
    private Estado estado;

    public Servicio(double precio){
        contIdServicio++;
        this.idServicio = contIdServicio;
        this.precio = precio;
        this.estado = Estado.DISPONIBLE;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
