public class GestorReservas extends Gestor<Reserva> {

    /* metodos */

    public Reserva buscar(Integer id) {
        for(Reserva reserva: lista){
            if(reserva.getId().equals(id)){
                return reserva;
            }
        }
        return null;
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Reserva.class);
    }
}
