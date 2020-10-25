package bank.data;

import java.util.Date;

public class Account {

    private long id;
    private String num;
    private String RIB;
    private Date creationDate;
    private double solde;
    private User user;
    private Card card;

    public Account(long id,String num, String RIB,Date creationDate, double solde, User user) {
        this.id = id;
        this.num=num;
        this.RIB = RIB;
        this.creationDate = creationDate;
        this.solde = solde;
        setUser(user);
        //this.card = new Card(id, number, "visa", cvv,  expirationMonth, expirationYear,this);
    }

    private String generateNumber(int taille){
        String number="";
        for(int i=0;i<taille;++i){
            int n = (int)(Math.random()*9);
            number += n;
        }
        return number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRIB() {
        return RIB;
    }

    public void setRIB(String RIB) {
        this.RIB = RIB;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if(this.user != null)
            this.user.removeAccount(this);
        this.user = user;
        this.user.addAccount(this);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", RIB='" + RIB + '\'' +
                ", creationDate=" + creationDate +
                ", solde=" + solde +
                '}';
    }
}
