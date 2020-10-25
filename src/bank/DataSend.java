package bank;

import java.io.Serializable;

public class DataSend implements Serializable {
    private String cardNum;
    private String year;
    private String month;
    private String cvv;
    private String numAccount;
    private double montant;

    public DataSend(String cardNum, String year, String month, String cvv, double montant,String numAccount) {
        this.cardNum = cardNum;
        this.year = year;
        this.month = month;
        this.cvv = cvv;
        this.numAccount = numAccount;
        this.montant = montant;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "DataSend{" +
                "cardNum='" + cardNum + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", cvv='" + cvv + '\'' +
                ", numAccount='" + numAccount + '\'' +
                ", montant=" + montant +
                '}';
    }
}
