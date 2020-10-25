package market.entities.payement;

import java.time.LocalDate;
import java.util.Date;

public class Check implements ClassicMode {
    private long id;
    private String propr;
    private String num;
    private Bank banck;
    private LocalDate date_effet;

    public Check(long id, String propr, String num, Bank banck, LocalDate date_effet) {
        this.id = id;
        this.propr = propr;
        this.num = num;
        this.banck = banck;
        this.date_effet = date_effet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropr() {
        return propr;
    }

    public void setPropr(String propr) {
        this.propr = propr;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Bank getBanck() {
        return banck;
    }

    public void setBanck(Bank Bank) {
        this.banck = banck;
    }

    public LocalDate getDate_effet() {
        return date_effet;
    }

    public void setDate_effet(LocalDate date_effet) {
        this.date_effet = date_effet;
    }

    @Override
    public String toString() {
        return "Cheque";
    }
}
