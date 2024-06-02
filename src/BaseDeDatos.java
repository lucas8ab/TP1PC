import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private List<Reserva> reservasPendientesDePago = new ArrayList<>();
    private List<Reserva> reservasConfirmadas = new ArrayList<>();
    private List<Reserva> reservasCanceladas = new ArrayList<>();
    private List<Reserva> reservasVerificadas = new ArrayList<>();
    private Asiento[][] matrizAsientos;

    public BaseDeDatos(int filas, int columnas){
        matrizAsientos = new Asiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizAsientos[i][j] = new Asiento();
                matrizAsientos[i][j].setId(i * columnas + j + 1);
            }
        }
    }

    public void reservar(){

    }

    public void pagar(){

    }

    public void cancelarValidar(){

    }

    public void verificar(){

    }

}
