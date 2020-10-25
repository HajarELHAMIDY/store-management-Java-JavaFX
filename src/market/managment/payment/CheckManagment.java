package market.managment.payment;


import market.dao.payement.CheckDAO;
import market.entities.payement.Bank;
import market.entities.payement.Check;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CheckManagment {
    private List<Check> checks;
    private CheckDAO checkDAO= CheckDAO.getInstance();
    private static CheckManagment checkManagment=null;
    private CheckManagment(){
        checks=checkDAO.getAll();
    }
    public static CheckManagment getInstance(){
        if (checkManagment==null) checkManagment=new CheckManagment();
        return checkManagment;
    }
    public Check add(String propr, String num, Bank banck, LocalDate date_effet){
//        if (name.length()<5 || description.length()<10){
//            return null;
//        }
        Check check=new Check(-1,propr,num,banck,date_effet);
        checkDAO.creat(check);
        checks.add(check);
        return check;

    }
    public boolean update(Check check,String propr, String num, Bank banck, LocalDate date_effet){
//        if (Check==null ||name.length()<5 || description.length()<10){
//            return false;
//        }
        check.setBanck(banck);
        check.setDate_effet(date_effet);
        check.setNum(num);
        check.setPropr(propr);
        checkDAO.update(check);
        return true;
    }
    public boolean delet(long id){
        Check check=getById(id);
        if (check==null)
            return false;
        checkDAO.delete(id);
        checks.remove(check);
        return true;
    }
    public List<Check> getAll(){
        return checks;
    }
    public Check getById(long id){
        for (Check c:checks){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }

}
