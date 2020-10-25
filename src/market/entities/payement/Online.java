package market.entities.payement;

public class Online implements PayementMode {
    private long id;
    private String numCart;
    private int annee_fin;
    private int mois_fin;
    private int cvv;

    public Online(long id, String numCart, int annee_fin, int mois_fin, int cvv) {
        this.id = id;
        this.numCart = numCart;
        this.annee_fin = annee_fin;
        this.mois_fin = mois_fin;
        this.cvv = cvv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumCart() {
        return numCart;
    }

    public void setNumCart(String numCart) {
        this.numCart = numCart;
    }

    public int getAnnee_fin() {
        return annee_fin;
    }

    public void setAnnee_fin(int annee_fin) {
        this.annee_fin = annee_fin;
    }

    public int getMois_fin() {
        return mois_fin;
    }

    public void setMois_fin(int mois_fin) {
        this.mois_fin = mois_fin;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Carte";
    }
}
