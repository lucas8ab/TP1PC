import java.util.NoSuchElementException;
import java.util.Random;

public class ProcesoDePago implements Runnable {
        private RegistroDeReserva registro;
        private Boolean errorInterruptedException = true;
        private int tiempoDeTrabajo;


    public ProcesoDePago(RegistroDeReserva registro, int tiempoDeTrabajo){
            this.registro = registro;
            this.tiempoDeTrabajo = tiempoDeTrabajo;
        }

        public void run(){
            while(!Thread.currentThread().isInterrupted() && errorInterruptedException){
                try{
                    Asiento asiento = registro.getAsientoPendienteDePago();
                    //90% pago aprobado, 10% pago rechazado
                    Random rand = new Random();
                    int probabilidad = rand.nextInt(100);
                    registro.eliminarReservaPendiente(asiento);
                    if (probabilidad < 90) {
                        registro.agregarReservaConfirmada(asiento);
                    } else {
                        asiento.asientoDescartado();
                        registro.agregarReservaCancelada(asiento);
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
            //System.out.println("Termino Proceso de pago");
        }
}
