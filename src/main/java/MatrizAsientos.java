import java.util.Random;

public class MatrizAsientos {
    private Asiento[][] matrizAsientos;
    public int filas;
    public int columnas;
    public int filaAleatoria=1;
    public int columnaAleatoria=1;
    public Random random;

    public MatrizAsientos(int filas, int columnas, Random random){
        this.filas = filas;
        this.columnas = columnas;
        this.random = random;
        matrizAsientos = new Asiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizAsientos[i][j] = new Asiento();
                matrizAsientos[i][j].setId(i * columnas + j + 1);
            }
        }
    }

    //metodo para devolver un asiento libre
    public synchronized Asiento getAsientoLibre(Asiento asiento) {
        filaAleatoria = random.nextInt(filas);
        columnaAleatoria = random.nextInt(columnas);
        if(existeAsientoLibre()){
            while(!matrizAsientos[filaAleatoria][columnaAleatoria].getEstado().equals(EstadosAsiento.LIBRE)){
                //si las variables random no encontraron un asiento libre busca otro
                filaAleatoria = random.nextInt(filas);
                columnaAleatoria = random.nextInt(columnas);
            }
            // sale del while cuando encuentra un asiento libre
            return matrizAsientos[filaAleatoria][columnaAleatoria];
        } else{
            // si no hay más asientos libres devuelve un asiento fantasma que no pertenece a la matriz
            return new Asiento();
        }
    }

    //metodo para saber si quedan asientos libres
    public synchronized boolean existeAsientoLibre(){
        int k = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(matrizAsientos[i][j].getEstado().equals(EstadosAsiento.LIBRE)){
                    k++;
                }
            }
        }
        if(k == 0){
            return false;
        }else{
            return true;
        }
    }

    //metodo para hacer string los estados de cada asiento para el Log
    public String matrizOcupacion(){
        String matrizOcupacion = "Matriz de ocupacion: " + "\n";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizOcupacion += matrizAsientos[i][j].getEstado() + "\t";
            }
            matrizOcupacion += "\n";
        }
        return matrizOcupacion;
    }

}
