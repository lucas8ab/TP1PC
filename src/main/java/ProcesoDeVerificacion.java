import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProcesoDeVerificacion implements Runnable {

    private RegistroDeReserva registro;
    private int tiempoDeTrabajo;
    private Boolean errorInterruptedException = true;

    public ProcesoDeVerificacion(RegistroDeReserva registro, int tiempoDeTrabajo){
        this.tiempoDeTrabajo = tiempoDeTrabajo;
        this.registro = registro;
    }

    public void run(){
        while(!Thread.currentThread().isInterrupted() && errorInterruptedException){
            try{
                Asiento asiento = registro.getAsientoConfirmado();
                registro.eliminarReservaConfirmada(asiento);
                registro.agregarReservaVerificada(asiento);
            } catch (NoSuchElementException e){
            }
            try {
                //TimeUnit.SECONDS.sleep(tiempoDeTrabajo);
                Thread.sleep(tiempoDeTrabajo);
            } catch (InterruptedException e){
                errorInterruptedException = false;
            }
        }
        //System.out.println("Termino Proceso de verificacion");
    }
}
