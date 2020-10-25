package market.entities;


public class Produit  {
    private long id;
    private String designation;
    private double prixAchat;
    private double prixVente;
    private int quantite;
    private Category category;

    public Produit(long id, String designation, double prixAchat,double prixVente,int quantite, Category category) {
        this.id = id;
        this.designation = designation;
        this.prixAchat=prixAchat;
        this.prixVente=prixVente;
        this.quantite=quantite;
        setCategory(category);
    }

    public Produit(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (this.category!=null)
            this.category.removeproduit(this);
        this.category = category;
        if (this.category!=null)
            this.category.addproduit(this);
    }

    @Override
    public String toString() {
        return id+": "+designation;
    }

}
