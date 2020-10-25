package bank;

import bank.data.Card;
import bank.managment.CardManagment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        CardManagment card=CardManagment.getInstance();
//        for (Card c:card.getAll()) {
//            System.out.println(c);
//            System.out.println(c.getAccount());
//            System.out.println(c.getAccount().getUser());
//        }
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day=localDate.getDayOfMonth();
        System.out.println(year+"   "+month+"   "+day);
    }
}
