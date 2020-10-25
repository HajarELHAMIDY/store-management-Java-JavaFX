package market;


import bank.data.Card;
import bank.managment.CardManagment;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import market.entities.LineOrder;
import market.entities.Order;
import market.entities.Produit;
import market.entities.payement.Cash;
import market.entities.payement.ClassicMode;
import market.fxml.FormeProduit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import market.managment.LineOrderManagment;
import market.managment.OrderManagment;
import market.managment.payment.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    FormeProduit interfaceProduit=new FormeProduit();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("market.fxml/client.market.fxml"));
        primaryStage.setTitle("Gestion Magazin");
        //interfaceProduit.initPane();
        //interfaceProduit.createContent();
        //interfaceProduit.initTableProduct();
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.getScene().getStylesheets().add("Mycss.css");
        primaryStage.show();

    }


    public static void main(String[] args) {
//        List<Object> parameter=new ArrayList();
//        parameter.add(new Integer(12));
//        parameter.add(new Double(23.67));
//        parameter.add(new String("imad"));
//        for (Object obj:parameter){
//            System.out.println(obj.getClass().getName());
//            switch (obj.getClass().getName()){
//                case "java.lang.Integer":
//                    System.out.println("intiger");
//                    break;
//                case "java.lang.Double":
//                    System.out.println("double");
//                    break;
//                case "java.lang.String":
//                    System.out.println("string");
//                    break;
//            }
//        }
       // System.out.println(j.getClass().getName());
        //launch(args);
//        PaymentManagment p=PaymentManagment.getInstance();
//        CashManagment c=CashManagment.getInstance();
//        ClassicModeManagment cc=ClassicModeManagment.getInstance();
//        DraftManagment d=DraftManagment.getInstance();
//        ItemDraftManagment dd=ItemDraftManagment.getInstance();
//        OnlineManagment o=OnlineManagment.getInstance();
//        PaymentModeManagment pp=PaymentModeManagment.getInstance();
//        if (p.getAll()==null && c.getAll()==null && d.getAll()==null && dd.getAll()==null && o.getAll()==null)
//            System.out.println(c.getClass().getSimpleName());
//        DatePicker ddd=new DatePicker();
//        LocalDate l=new LocalDate();
//        Date
//        System.out.println(ddd.getValue());

//        CardManagment card=CardManagment.getInstance();
//        for (Card c:card.getAll()){
//            System.out.println(c);
//            System.out.println(c.getAccount());
//            System.out.println(c.getAccount().getUser());
//        }
//        LineOrderManagment lineOrderManagment=LineOrderManagment.getInstance();
//        for (LineOrder o:lineOrderManagment.getAll()){
//            System.out.println(o);
//            System.out.println(o.getOrder());
//        }
        String tab[]={"AA","BB","CC","AA","BB","DD","CC"};
        Map<String,Integer> r=new HashMap();
        for (String s:tab){
            if (r.containsKey(s))
                r.put(s,r.get(s)+1);
            else
                r.put(s,1);
        }
        System.out.println(r);
    }
}
