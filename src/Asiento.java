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

    public void asientoOcupado() {
        estado = EstadosAsiento.OCUPADO;
    }

    public void asientoDescartado() {
        estado = EstadosAsiento.DESCARTADO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
