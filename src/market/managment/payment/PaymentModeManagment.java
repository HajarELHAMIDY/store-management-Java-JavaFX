package market.managment.payment;


import market.entities.payement.PayementMode;

import java.util.List;

public class PaymentModeManagment {
    private ClassicModeManagment classicModeManagment = ClassicModeManagment.getInstance();
    private OnlineManagment onlineManagment = OnlineManagment.getInstance();
    private DraftManagment draftManagment = DraftManagment.getInstance();
    public static String mode[]={"Espece","Cheque","Carte","Traite"};
    private static PaymentModeManagment paymentModeManagment=null;
    private PaymentModeManagment(){
    }
    public static PaymentModeManagment getInstance(){
        if (paymentModeManagment==null) paymentModeManagment=new PaymentModeManagment();
        return paymentModeManagment;
    }
    public PayementMode getById(long id, String type){

        switch (type){
            case "Check":
            case "Cash":return classicModeManagment.getById(id,type);

            case "Online":return onlineManagment.getById(id);

            case "Draft":return draftManagment.getById(id);

            default:return null;
        }
    }
}
