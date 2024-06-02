public class Reserva {
    private boolean checked = false;
    private int id;

    public Reserva(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void checked(){
        checked=true;
    }
}
