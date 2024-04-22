public class Asiento {

    private EstadosAsiento estado;
    private int id;

    public Asiento() {
        this.estado = EstadosAsiento.LIBRE;
        this.id = 0;
    }

    public EstadosAsiento getEstado() {
        return estado;
    }

    public synchronized void asientoOcupado() {
        if(estado.equals(EstadosAsiento.LIBRE)){
            estado = EstadosAsiento.OCUPADO;
        }
    }

    public synchronized void asientoDescartado() {
        if(estado.equals(EstadosAsiento.OCUPADO)){
            estado = EstadosAsiento.DESCARTADO;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
