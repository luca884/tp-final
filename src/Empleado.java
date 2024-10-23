public class Empleado extends Persona {
    /* atributos */
    private Double salario;
    private Double valor_hora;
    private Integer cantidad_horas;
    private Puesto puesto;

    /* constructor */
    public Empleado(String nombre, String apellido, String dni, Double valor_hora, Integer cantidad_horas, Puesto puesto) {
        super(nombre, apellido, dni);
        this.valor_hora = valor_hora;
        this.cantidad_horas = cantidad_horas;
        this.puesto = puesto;
        this.salario = calcularSalario();
    }

    /* metodos */
    public Double calcularSalario(){
        return valor_hora * cantidad_horas;
    }

    /* getters y setters*/
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(Double valor_hora) {
        this.valor_hora = valor_hora;
    }

    public Integer getCantidad_horas() {
        return cantidad_horas;
    }

    public void setCantidad_horas(Integer cantidad_horas) {
        this.cantidad_horas = cantidad_horas;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
}
