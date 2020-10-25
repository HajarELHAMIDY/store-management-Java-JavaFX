package market.managment.payment;

import market.dao.payement.OnlineDAO;
import market.entities.payement.Online;

import java.util.List;

public class OnlineManagment {
    private List<Online> onlines;
    private OnlineDAO onlineDAO= OnlineDAO.getInstance();
    private static OnlineManagment onlineManagment=null;
    private OnlineManagment(){
        onlines=onlineDAO.getAll();
    }
    public static OnlineManagment getInstance(){
        if (onlineManagment==null) onlineManagment=new OnlineManagment();
        return onlineManagment;
    }
    public Online add(String numCart, int annee_fin, int mois_fin, int cvv){
//        if (name.length()<5 || description.length()<10){
//            return false;
//        }
        Online online=new Online(-1,numCart,annee_fin,mois_fin,cvv);
        onlineDAO.creat(online);
        onlines.add(online);
        return online;

    }
    public boolean update(Online online,String numCart, int annee_fin, int mois_fin, int cvv){
//        if (Online==null ||name.length()<5 || description.length()<10){
//            return false;
//        }
        online.setAnnee_fin(annee_fin);
        online.setCvv(cvv);
        online.setMois_fin(mois_fin);
        online.setNumCart(numCart);
        onlineDAO.update(online);
        return true;
    }
    public boolean delet(long id){
        Online online=getById(id);
        if (online==null)
            return false;
        onlineDAO.delete(id);
        onlines.remove(online);
        return true;
    }
    public List<Online> getAll(){
        return onlines;
    }
    public Online getById(long id){
        for (Online o:onlines){
            if (o.getId()==id){
                return o;
            }
        }
        return null;
    }

}
