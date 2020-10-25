package market.managment.payment;

import market.dao.payement.ItemDraftDAO;
import market.entities.payement.ClassicMode;
import market.entities.payement.Draft;
import market.entities.payement.ItemDraft;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ItemDraftManagment {
    private List<ItemDraft> itemDrafts;
    private ItemDraftDAO itemDraftDAO= ItemDraftDAO.getInstance();
    private static ItemDraftManagment itemDraftManagment=null;
    private ItemDraftManagment(){
        itemDrafts=itemDraftDAO.getAll();
    }
    public static ItemDraftManagment getInstance(){
        if (itemDraftManagment==null) itemDraftManagment=new ItemDraftManagment();
        return itemDraftManagment;
    }
    public boolean add(double montant, LocalDate date_prevue, LocalDate date_effectue, ClassicMode item, Draft draft){
//        if (designation.length()<5 || prix<=0 || category==null){
//            return false;
//        }
        ItemDraft itemDraft=new ItemDraft(-1,montant,date_prevue, date_effectue, item, draft);
        itemDraftDAO.creat(itemDraft);
        itemDrafts.add(itemDraft);

        return true;

    }
    public boolean add(Draft draft,ItemDraft itemDraft){
        if (draft==null || itemDraft==null){
            return false;
        }
        itemDraft.setDraft(draft);
        itemDraftDAO.creat(itemDraft);
        itemDrafts.add(itemDraft);
        return true;

    }
    public boolean update(ItemDraft itemDraft, double montant, LocalDate date_prevue, LocalDate date_effectue, ClassicMode item, Draft draft){
//        if (ItemDraft==null ||designation.length()<5 || prix<=0 || category==null){
//            return false;
//        }
        itemDraft.setMontant(montant);
        itemDraft.setDate_effectue(date_effectue);
        itemDraft.setDate_prevue(date_prevue);
        itemDraft.setDraft(draft);
        itemDraft.setItem(item);
        itemDraftDAO.update(itemDraft);
        return true;
    }
    public boolean delet(long id){
        ItemDraft itemDraft=getById(id);
        if (itemDraft==null)
            return false;
        itemDraftDAO.delete(id);
        itemDrafts.remove(itemDraft);
        return true;
    }
    public List<ItemDraft> getAll(){
        if (itemDrafts==null)
            itemDrafts=itemDraftDAO.getAll();
        return itemDrafts;
    }
    public ItemDraft getById(long id){
        for (ItemDraft i:itemDrafts){
            if (i.getId()==id){
                return i;
            }
        }
        return null;
    }
}
