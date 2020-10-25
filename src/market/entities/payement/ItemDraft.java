package market.entities.payement;

import java.time.LocalDate;
import java.util.Date;

public class ItemDraft {
    private long id;
    private double montant;
    private LocalDate date_prevue;
    private LocalDate date_effectue;
    private ClassicMode item;
    private Draft draft;

    public ItemDraft(long id, double montant, LocalDate date_prevue, LocalDate date_effectue, ClassicMode item,Draft draft) {
        this.id = id;
        this.montant = montant;
        this.date_prevue = date_prevue;
        this.date_effectue = date_effectue;
        this.item = item;
        setDraft(draft);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate_prevue() {
        return date_prevue;
    }

    public void setDate_prevue(LocalDate date_prevue) {
        this.date_prevue = date_prevue;
    }

    public LocalDate getDate_effectue() {
        return date_effectue;
    }

    public void setDate_effectue(LocalDate date_effectue) {
        this.date_effectue = date_effectue;
    }

    public ClassicMode getItem() {
        return item;
    }

    public void setItem(ClassicMode item) {
        this.item = item;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        if (this.draft!=null)
            this.draft.removeItemDraft(this);
        this.draft = draft;
        if (this.draft!=null)
            this.draft.addItemDraft(this);
    }
}
