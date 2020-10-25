package market.entities;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private long id;
    private String nom;
    private String description;
    private List<Produit> produits;

    public Category(long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.produits = new ArrayList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Produit> getProduits(){
        return produits;
    }

    public void addproduit(Produit p){
        produits.add(p);
    }

    public void removeproduit(Produit p){
        produits.remove(p);
    }

    @Override
    public String toString() {
        return nom;
    }
}
