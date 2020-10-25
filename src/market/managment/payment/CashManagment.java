package market.managment.payment;

import market.dao.payement.CashDAO;
import market.entities.payement.Cash;
import market.entities.payement.PayementMode;

import java.util.List;

public class CashManagment {
    private List<Cash> cashs;
    private CashDAO cashDAO= CashDAO.getInstance();
    private static CashManagment cashManagment=null;
    private CashManagment(){
        cashs=cashDAO.getAll();
    }
    public static CashManagment getInstance(){
        if (cashManagment==null) cashManagment=new CashManagment();
        return cashManagment;
    }
    public Cash add(){
        Cash cash=new Cash(-1);
        cashDAO.creat(cash);
        cashs.add(cash);
        return cash;

    }
    public boolean update(Cash cash){

        cashDAO.update(cash);
        return true;
    }
    public boolean delet(long id){
        Cash cash=getById(id);
        if (cash==null)
            return false;
        cashDAO.delete(id);
        cashs.remove(cash);
        return true;
    }
    public List<Cash> getAll(){
        return cashs;
    }
    public Cash getById(long id){
        for (Cash c:cashs){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }

}
