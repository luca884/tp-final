import java.util.HashSet;

public class Gestor<T> {
    /* atributos */
    public HashSet<T> lista = new HashSet<>();

    /* constructores */
    public Gestor() {
    }

    public Gestor(HashSet<T> lista) {
        this.lista = lista;
    }

    /* metodos */
    public void agregar(T item) {
        lista.add(item);
    }

    public void eliminar(T item) {
        lista.remove(item);
    }

    public void mostrar(T item) {
        /* todo: implementar los toString para que funcione bien */
        System.out.println(item);
    }
    public void mostrar() {
        for (T item : lista) {
            mostrar(item);
        }
    }

    public T buscar(T item) {
        /*
            este método no tiene mucho sentido
            sobreescribir en cada clase derivada
            para buscar por el campo identificador o el que sea conveniente
         */
        for (T _item : lista) {
            if(_item.equals(item)){
                return _item;
            }
        }
        return null;
    }

    /* getters y setters */
    public HashSet<T> getLista() {
        return lista;
    }

    public void setLista(HashSet<T> lista) {
        this.lista = lista;
    }
}
