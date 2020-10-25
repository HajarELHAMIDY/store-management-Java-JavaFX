package market.managment.payment;


import market.dao.payement.DraftDAO;
import market.entities.payement.Draft;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DraftManagment {
    private List<Draft> drafts;
    private DraftDAO draftDAO= DraftDAO.getInstance();
    private static DraftManagment draftManagment=null;
    private DraftManagment(){
        drafts=draftDAO.getAll();
    }
    public static DraftManagment getInstance(){
        if (draftManagment==null) draftManagment=new DraftManagment();
        return draftManagment;
    }
    public Draft add(int nbTraita, LocalDate date_debut, double rest){
        if (nbTraita<2 || rest<0){
            return null;
        }
        Draft draft=new Draft(-1,nbTraita,date_debut,rest);
        draftDAO.creat(draft);
        drafts.add(draft);
        return draft;

    }
    public boolean update(Draft draft,int nbTraita,LocalDate date_debut,double rest){
        if (draft==null ){
            return false;
        }
        draft.setDate_debut(date_debut);
        draft.setNbTraita(nbTraita);
        draft.setRest(rest);
        draftDAO.update(draft);
        return true;
    }
    public boolean update(Draft draft,double total){
        if (draft==null ){
            return false;
        }
        draft.calculeRest(total);
        draftDAO.update(draft);
        return true;
    }
    public boolean delet(long id){
        Draft draft=getById(id);
        if (draft==null)
            return false;
        draftDAO.delete(id);
        drafts.remove(draft);
        return true;
    }
    public List<Draft> getAll(){
        return drafts;
    }
    public Draft getById(long id){
        for (Draft d:drafts){
            if (d.getId()==id){
                return d;
            }
        }
        return null;
    }

}
