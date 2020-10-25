package market.entities.payement;

public class Cash implements ClassicMode {
    private long id;

    public Cash(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Espece";
    }
}
