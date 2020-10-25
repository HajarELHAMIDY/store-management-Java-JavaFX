package market.managment.payment;

import market.entities.payement.ClassicMode;

public class ClassicModeManagment {

    private CheckManagment checkManagment = CheckManagment.getInstance();
    private CashManagment cashManagment = CashManagment.getInstance();
    private static ClassicModeManagment classicModeManagment=null;
    public static String mode[]={"Espece","Cheque"};
    private ClassicModeManagment(){ }
    public static ClassicModeManagment getInstance(){
        if (classicModeManagment==null) classicModeManagment=new ClassicModeManagment();
        return classicModeManagment;
    }
    public ClassicMode getById(long id,String type){

        switch (type){
            case "Check":return checkManagment.getById(id);

            case "Cash":return cashManagment.getById(id);

            default:return null;
        }
    }
}
