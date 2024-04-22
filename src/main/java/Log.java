import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class Log extends Thread {
    private RegistroDeReserva registro;
    private MatrizAsientos matrizAsientos;
    long startTime;
    private int tiempoDelPrograma;
    private int tiempoDeImpresionLog;

    private static final Logger logger = Logger.getLogger(Log.class.getName());
    private static final String LOG_FILE_NAME = "informacion.log";
    private FileHandler fileHandler;
    private SimpleFormatter formatter;

    public Log(RegistroDeReserva registro, MatrizAsientos matrizAsientos, long startTime, int tiempoDelPrograma, int tiempoDeImpresionLog) {
        try {
            this.registro = registro;
            this.matrizAsientos = matrizAsientos;
            this.startTime = startTime;
            this.tiempoDelPrograma = tiempoDelPrograma;
            this.tiempoDeImpresionLog= tiempoDeImpresionLog;

            this.fileHandler = new FileHandler(LOG_FILE_NAME);
            this.formatter = new SimpleFormatter();
            this.fileHandler.setFormatter(this.formatter);
            logger.addHandler(this.fileHandler);
        } catch (IOException e) {
            logger.severe("Error initializing logger: " + e.getMessage());
        }
    }

    public void run() {

        //imprimo en el Log cada 200 ms (tiempoDeImpresionLog) hasta llegar al tiempoDelPrograma
        for(int i=0; i<tiempoDelPrograma*1000/tiempoDeImpresionLog;i++){
            logger.info("Información actual: " +"\n"+ registro.informacion());
            try {
                Thread.sleep(tiempoDeImpresionLog);
            } catch (InterruptedException e){
            }
        }

        //imprimir matriz de ocupacion
        logger.warning("\n"+matrizAsientos.matrizOcupacion()+"\n");

        //imprimo una vez mas la informacion de los registros de canceladas y verificadas
        logger.info("Información final: " +"\n"+ registro.informacion());

        //imprimir el tiempo que demoro el programa
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.log(Level.OFF, "\n"+"El programa demoro: "+ totalTime + " ms");
    }
}
