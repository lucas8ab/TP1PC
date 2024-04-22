import java.util.NoSuchElementException;

public class ProcesoDeReserva implements Runnable {
    private MatrizAsientos matrizAsientos;
    private RegistroDeReserva registro;
    private int tiempoDeTrabajo;
    private boolean errorInterruptedException = true;
    private Asiento asiento = new Asiento();

    public ProcesoDeReserva(MatrizAsientos matrizAsientos, RegistroDeReserva registro, int tiempoDeTrabajo){
        this.matrizAsientos = matrizAsientos;
        this.registro = registro;
        this.tiempoDeTrabajo = tiempoDeTrabajo;
    }

    public void run(){
        while(!Thread.currentThread().isInterrupted() && errorInterruptedException){
            try{
                asiento = matrizAsientos.getAsientoLibre(asiento);
                if(asiento.getId() != 0 && asiento.getEstado().equals(EstadosAsiento.LIBRE)){
                    //cambiar asiento a ocupado
                    asiento.asientoOcupado();
                    //agregar asiento a reservas pendientes de pago
                    registro.agregarReservaPendienteDePago(asiento);
                }
            } catch (NoSuchElementException e){
            }
            try {
                //TimeUnit.SECONDS.sleep(tiempoDeTrabajo);
                Thread.sleep(tiempoDeTrabajo);
            } catch (InterruptedException e){
                errorInterruptedException = false;
            }
        }
        //System.out.println("Termino Proceso de reserva");
    }
}