import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int tiempoDelPrograma = 10; // el tiempo del programa está expresado en segundos
        int tiempoDeImpresionLog = 200; // cada cuanto tiempo va a imprimir el Log expresado en milisegundos
        int filas = 31; //filas de la matriz de asientos
        int columnas = 6; //columnas de la matriz de asientos
        Random random = new Random();
        MatrizAsientos matrizAsientos = new MatrizAsientos(filas,columnas,random);
        RegistroDeReserva registroDeReserva = new RegistroDeReserva(random);

        //iniciamos 3 hilos en Proceso de reservas
        ProcesoDeReserva procesoDeReserva1 = new ProcesoDeReserva(matrizAsientos, registroDeReserva, 100);
        Thread thread1 = new Thread(procesoDeReserva1);

        ProcesoDeReserva procesoDeReserva2 = new ProcesoDeReserva(matrizAsientos, registroDeReserva,100);
        Thread thread2 = new Thread(procesoDeReserva2);

        ProcesoDeReserva procesoDeReserva3 = new ProcesoDeReserva(matrizAsientos, registroDeReserva, 100);
        Thread thread3 = new Thread(procesoDeReserva3);

        thread1.start();
        thread2.start();
        thread3.start();

        //iniciamos 2 hilos de Proceso de pago
        ProcesoDePago procesoDePago1 = new ProcesoDePago(registroDeReserva, 200);
        Thread thread4 = new Thread(procesoDePago1);
        thread4.start();

        ProcesoDePago procesoDePago2 = new ProcesoDePago(registroDeReserva, 200);
        Thread thread5 = new Thread(procesoDePago2);
        thread5.start();

        //iniciamos 3 hilos de Proceso de cancelacion/validacion
        ProcesoDeCancelacionValidacion procesoDeCancelacionValidacion1 = new ProcesoDeCancelacionValidacion(registroDeReserva, 100);
        Thread thread6 = new Thread(procesoDeCancelacionValidacion1);
        thread6.start();

        ProcesoDeCancelacionValidacion procesoDeCancelacionValidacion2 = new ProcesoDeCancelacionValidacion(registroDeReserva, 100);
        Thread thread7 = new Thread(procesoDeCancelacionValidacion2);
        thread7.start();

        ProcesoDeCancelacionValidacion procesoDeCancelacionValidacion3 = new ProcesoDeCancelacionValidacion(registroDeReserva, 100);
        Thread thread8 = new Thread(procesoDeCancelacionValidacion3);
        thread8.start();

        //iniciamos 2 hilos de Proceso de verificacion
        ProcesoDeVerificacion procesoDeVerificacion1 = new ProcesoDeVerificacion(registroDeReserva, 100);
        Thread thread9 = new Thread(procesoDeVerificacion1);
        thread9.start();

        ProcesoDeVerificacion procesoDeVerificacion2 = new ProcesoDeVerificacion(registroDeReserva, 100);
        Thread thread10 = new Thread(procesoDeVerificacion2);
        thread10.start();

        //iniciamos el hilo correspondiente al Log
        //Log log = new Log(registroDeReserva, matrizAsientos, startTime, tiempoDelPrograma, tiempoDeImpresionLog);
        Thread thread11 = new Thread(new Log(registroDeReserva, matrizAsientos, startTime, tiempoDelPrograma, tiempoDeImpresionLog));
        thread11.start();


        //el main duerme el tiempo del programa indicado antes de interrumpir los hilos
        try {
            TimeUnit.SECONDS.sleep(tiempoDelPrograma);
        } catch (InterruptedException e){

        }

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();
        thread5.interrupt();
        thread6.interrupt();
        thread7.interrupt();
        thread8.interrupt();
        thread9.interrupt();
        thread10.interrupt();

    //en adicion imprimo la cantidad de reservas para chequear
        registroDeReserva.reservas();

        System.out.println("Termino el hilo main");
    }
}