package Varios;

public class Mantenimiento {
    private String dni;
    private String descripcion;
    private double costo;
    private String estado;

    public Mantenimiento(String dni, String descripcion, double costo, String estado) {
        this.dni = dni;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estado = estado;
    }

    public Mantenimiento() {

    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "dni='" + dni + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mantenimiento that = (Mantenimiento) obj;
        return dni.equals(that.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }


}
