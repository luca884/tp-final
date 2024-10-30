import java.util.Objects;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private final String dni;
    protected NivelPermisos nivelPermisos;

    protected Persona(String nombre, String apellido, String dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    /* toString */
    @Override
    public String toString() {
        return "dni:%s\nnombre: %s\napellido: %s\nnivel permisos: %s".formatted(dni, nombre, apellido, nivelPermisos);
    }

    /* equals y hashcode  */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Persona persona = (Persona) object;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public NivelPermisos getNivelPermisos(){
        return this.nivelPermisos;
    }

    public void setNivelPermisos(NivelPermisos nivelPermisos){
        this.nivelPermisos = nivelPermisos;
    }
}
