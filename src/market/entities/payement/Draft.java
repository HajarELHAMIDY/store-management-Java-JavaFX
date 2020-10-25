package market.entities.payement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Draft implements PayementMode {
    private long id;
    private int nbTraita;
    private LocalDate date_debut;
    private double rest;
    private List<ItemDraft> itemsDraft;

    public Draft(long id, int nbTraita, LocalDate date_debut, double rest) {
        this.id = id;
        this.nbTraita = nbTraita;
        this.date_debut = date_debut;
        this.rest = rest;
        this.itemsDraft = new ArrayList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNbTraita() {
        return nbTraita;
    }

    public void setNbTraita(int nbTraita) {
        this.nbTraita = nbTraita;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    public List<ItemDraft> getItemsDraft() {
        return itemsDraft;
    }

    public void setItemsDraft(List<ItemDraft> itemsDraft) {
        this.itemsDraft = itemsDraft;
    }

    public void addItemDraft(ItemDraft itemDraft){
        itemsDraft.add(itemDraft);
    }
    public void removeItemDraft(ItemDraft itemDraft){
        itemsDraft.remove(itemDraft);
    }
    public void calculeRest(Double total){
        double totalPayer=0;
        for (ItemDraft id:itemsDraft){
            totalPayer+=id.getMontant();
        }
        this.rest=total-totalPayer;
    }
    @Override
    public String toString() {
        return "Traite";
    }
}
