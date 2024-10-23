import java.time.LocalDate;

public class Reserva {
    /* atributos */
    static private Integer contador_id = 0;
    private Integer id;

    private Servicio servicio;
    private Cliente cliente;
    private LocalDate fecha;

    /* constructores */
    public Reserva(Servicio servicio, Cliente cliente, LocalDate fecha) {
        this.servicio = servicio;
        this.cliente = cliente;
        this.fecha = fecha;
        contador_id++;
        this.id = contador_id;
    }

    /* getters y setters */
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }
}
