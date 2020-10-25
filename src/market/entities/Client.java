package market.entities;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private Address address;
    private Collection<Order> orders;

    public Client(long id, String nom, String prenom,String phone,String email,Address address) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone=phone;
        this.email=email;
        this.address=address;
        orders = new ArrayList();
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public void addOrder(Order o) {
        orders.add(o);
    }


    public void removeOrder(Order o) {
        orders.remove(o);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return id +": "+  nom + ' ' + prenom;
    }
}
