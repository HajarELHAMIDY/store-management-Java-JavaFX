package market.entities;

public class LineOrder {
    private long id;
    private Produit produit;
    private int quantite;
    private double total;
    private Order order;

    public LineOrder(long id, Produit produit, int quantite,Order order) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        produit.setQuantite(produit.getQuantite()-quantite);
        setOrder(order);
        updateTotal();
    }
    public LineOrder(long id, Produit produit, int quantite,Order order,double total) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.total=total;
        setOrder(order);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
        this.updateTotal();
    }
    public void setOrder(Order order) {
        if (this.order!=null) {
            this.order.removeLineOrder(this);
            this.order.updateTotal();
        }
        this.order = order;
        if (this.order!=null) {
            this.order.addLineOrder(this);
            this.order.updateTotal();
        }
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        this.updateTotal();

    }

    public double getTotal() {
        return total;
    }

    public Order getOrder() {
        return order;
    }

    private void updateTotal() {
        this.total=produit.getPrixVente()*quantite;
        this.order.updateTotal();
    }

}
