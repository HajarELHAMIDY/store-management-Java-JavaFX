package market.entities;

import market.entities.payement.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Order {
    private long id;
    private LocalDate date;
    private Collection<LineOrder> lineOrders;
    private Client client;
    private double total=0;
    private Payment payement;
    private short orderType;

    public Order(long id, LocalDate date, Client client,short orderType) {
        this.id = id;
        this.date = date;
        this.lineOrders = new ArrayList();
        this.orderType=orderType;
        this.setClient(client);
    }
    public Order(long id, LocalDate date, Client client,double total,short orderType) {
        this.id = id;
        this.date = date;
        this.lineOrders = new ArrayList();
        //this.total=total;
        this.orderType=orderType;
        this.setClient(client);
        updateTotal();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Collection<LineOrder> getLineOrders() {
        return lineOrders;
    }

    public void setLineOrders(Collection<LineOrder> lineCommandes) {
        this.lineOrders = lineCommandes;
        updateTotal();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client!=null)
            this.client.removeOrder(this);
        this.client = client;
        if (this.client!=null)
            this.client.addOrder(this);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addLineOrder(LineOrder l){
        lineOrders.add(l);
        updateTotal();
    }

    public void removeLineOrder(LineOrder l){
        lineOrders.remove(l);
        updateTotal();
    }
    public void updateTotal(){
        total=0;
        for (LineOrder l:lineOrders){
            total+=l.getTotal();
        }
    }

    public short getShortOrderType() {
        return orderType;
    }

    public void setOrderType(short orderType) {
        this.orderType = orderType;
    }

    public Payment getPayement() {
        return payement;
    }
    public void setPayementOrder(Payment payement) {
        this.payement = payement;
    }
    public void setPayement(Payment payement) {
        this.payement = payement;
        if (this.payement!=null)
            this.payement.setOrderPayement(this);
    }
    public String getOrderType(){
        switch (orderType){
            case 1:return "Non Payee";
            case 2:return "Payee";
            case 3:return "Annuler";
        }
        return "Sans Etat";
    }
    public LineOrder getLineByProduct(Produit p){
        for (LineOrder l:lineOrders){
            if (l.getProduit()==p)
                return l;
        }
        return null;
    }
}
