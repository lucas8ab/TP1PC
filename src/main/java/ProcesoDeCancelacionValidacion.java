import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProcesoDeCancelacionValidacion implements Runnable {

    private RegistroDeReserva registro;
    private Boolean errorInterruptedException = true;
    private int tiempoDeTrabajo;

    public ProcesoDeCancelacionValidacion(RegistroDeReserva registro, int tiempoDeTrabajo){
           this.registro = registro;
           this.tiempoDeTrabajo = tiempoDeTrabajo;
    }

    public void run(){
            while(!Thread.currentThread().isInterrupted() && errorInterruptedException){
                try{
                    Asiento asiento = registro.getAsientoConfirmado();
                    //10% de cancelar, 90% de no cancelar
                    Random rand = new Random();
                    int probabilidad = rand.nextInt(100);
                    registro.eliminarReservaConfirmada(asiento);
                    if (probabilidad < 90) {
                        registro.agregarReservaVerificada(asiento);
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
        //System.out.println("Termino Proceso de cancelacion/validacion");
        }
    }
