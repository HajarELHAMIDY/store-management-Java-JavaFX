package bank.data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Card {
    private long id;
    private String number;
    private String type;
    private String cvv;
    private Date creationDate;
    private String  expirationMonth;
    private String expirationYear;
    private Account account;

    public Card(long id, String number, String type, String cvv,Date creationDate, String expirationMonth, String expirationYear, Account account) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.cvv = cvv;
        this.creationDate = creationDate;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public boolean verificationDate(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        if (Integer.parseInt(expirationYear)>=year){
            return true;
        }else {
            if (Integer.parseInt(expirationYear)==year){
                if (Integer.parseInt(expirationMonth)>=month){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", cvv='" + cvv + '\'' +
                ", creationDate=" + creationDate +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", account=" + account +
                '}';
    }
}
