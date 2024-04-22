import java.util.*;

public class RegistroDeReserva {

    private List<Asiento> reservasPendientesDePago = new ArrayList<>();
    private List<Asiento> reservasConfirmadas = new ArrayList<>();
    private List<Asiento> reservasCanceladas = new ArrayList<>();
    private List<Asiento> reservasVerificadas = new ArrayList<>();
    private Random random = new Random();

    public RegistroDeReserva(Random random){
        this.random = random;
    }

    public synchronized void agregarReservaPendienteDePago(Asiento asiento) {
        if(!reservasPendientesDePago.contains(asiento)){
            reservasPendientesDePago.add(asiento);
        }
    }

    public synchronized void agregarReservaConfirmada(Asiento asiento) {
        if(!reservasConfirmadas.contains(asiento)){
            reservasConfirmadas.add(asiento);
        }
    }

    public synchronized void agregarReservaCancelada(Asiento asiento) {
        if(!reservasCanceladas.contains(asiento)){
            reservasCanceladas.add(asiento);
        }
    }

    public synchronized void agregarReservaVerificada(Asiento asiento) {
        if(!reservasVerificadas.contains(asiento)){
            reservasVerificadas.add(asiento);
        }
    }

    public synchronized void eliminarReservaPendiente(Asiento asiento) {
        if(reservasPendientesDePago.contains(asiento)){
            reservasPendientesDePago.remove(asiento);
        }
    }

    public synchronized void eliminarReservaConfirmada(Asiento asiento) {
        if(reservasConfirmadas.contains(asiento)){
            reservasConfirmadas.remove(asiento);
        }
    }

    // metodo para devolver un asiento al azar del registro de resevas pendientes de pago
    public synchronized Asiento getAsientoPendienteDePago(){
        if(reservasPendientesDePago.isEmpty()){
            throw new NoSuchElementException("No hay asientos confirmados disponibles");
        }
        int indiceAleatorio = random.nextInt(reservasPendientesDePago.size());
        return reservasPendientesDePago.get(indiceAleatorio);
    }

    // metodo para devolver un asiento al azar del registro de resevas confirmadas
    public synchronized Asiento getAsientoConfirmado() {
            if (reservasConfirmadas.isEmpty()) {
                throw new NoSuchElementException("No hay asientos confirmados disponibles");
            }
            int indiceAleatorio = random.nextInt(reservasConfirmadas.size());
            return reservasConfirmadas.get(indiceAleatorio);
    }

    // metodo que convierte en string la cantidad de reservas canceladas y verificadas para el Log
    public String informacion(){
        String informacion = "Reservas canceladas: " + reservasCanceladas.size() + "\n";
        informacion += "Reservas aprobadas (verificadas): " + reservasVerificadas.size() + "\n";
        return informacion;
    }

    //método auxiliar para ver cuantas reservas hay en cada lista
    public void reservas(){
        String reservas = "Reservas pendientes de pago: " + reservasPendientesDePago.size() + "\n";
        reservas += "Reservas confirmadas: " + reservasConfirmadas.size() + "\n";
        reservas += "Reservas canceladas: " + reservasCanceladas.size() + "\n";
        reservas += "Reservas verificadas: " + reservasVerificadas.size() + "\n";

        System.out.println(reservas);
    }
}