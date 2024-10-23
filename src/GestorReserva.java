public class GestorReserva extends Gestor<Reserva> {

    /* metodos */

    public Reserva buscar(Integer id) {
        for(Reserva reserva: lista){
            if(reserva.getId().equals(id)){
                return reserva;
            }
        }
        return null;
    }
}
