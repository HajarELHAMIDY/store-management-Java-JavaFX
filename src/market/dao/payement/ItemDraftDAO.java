package market.dao.payement;

import market.connection.DBConnection;
import market.dao.DAO;
import market.entities.payement.ItemDraft;
import market.managment.payment.ClassicModeManagment;
import market.managment.payment.DraftManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDraftDAO implements DAO<ItemDraft> {
    private DBConnection dbConnection=DBConnection.getConnection();
    private static ItemDraftDAO itemDraftDAO=null;
    private DraftManagment draftManagment=DraftManagment.getInstance();
    private ClassicModeManagment classicModeManagment= ClassicModeManagment.getInstance();
    static String TABLE="itemDraft";
    static String COLUMN[]={"id","montant","date_prevue","date_effectue","idDraft","typePayement","idTypePayment"};

    private ItemDraftDAO() { }
    public static ItemDraftDAO getInstance(){
        if (itemDraftDAO==null)itemDraftDAO=new ItemDraftDAO();
        return itemDraftDAO;
    }
    public boolean creat(ItemDraft i) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+","+COLUMN[4]+", "+COLUMN[5]+","+COLUMN[6]+") VALUES (?,?,?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,i.getMontant(),i.getDate_prevue(),i.getDate_effectue(),i.getDraft().getId(),i.getItem().getClass().getSimpleName(),i.getItem().getId());
        i.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(ItemDraft i){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+","+COLUMN[4]+"=?,"+COLUMN[5]+","+COLUMN[6]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,i.getMontant(),i.getDate_prevue(),i.getDate_effectue(),i.getDraft().getId(),i.getItem().getClass().getSimpleName(),i.getItem().getId(),i.getId());
        return false;
    }

    public ItemDraft getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new ItemDraft(r.getLong(COLUMN[0]),r.getDouble(COLUMN[1]),r.getDate(COLUMN[2]).toLocalDate(),r.getDate(COLUMN[3]).toLocalDate(),classicModeManagment.getById(r.getLong(COLUMN[5]),r.getString(COLUMN[6])),draftManagment.getById(r.getLong(COLUMN[4])));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ItemDraft> getAll(){
        List<ItemDraft> itemDrafts=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                itemDrafts.add(new ItemDraft(r.getLong(COLUMN[0]),r.getDouble(COLUMN[1]),r.getDate(COLUMN[2]).toLocalDate(),r.getDate(COLUMN[3]).toLocalDate(),classicModeManagment.getById(r.getLong(COLUMN[4]),r.getString(COLUMN[5])),draftManagment.getById(r.getLong(COLUMN[4]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemDrafts;
    }

    public List<ItemDraft> getAll(String keyword) {
//        List<ItemDraft> ItemDrafts=new ArrayList();
//        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[1]+" like '%?%'";
//        CategoryManagment categorys = CategoryManagment.getInstance();
//        try {
//            ResultSet r=dbConnection.executeQuery(sql,keyword);
//            while (r.next())
//                ItemDrafts.add(new ItemDraft(r.getInt(COLUMN[0]),productManagment.getById(r.getLong(COLUMN[3])),r.getInt(COLUMN[1]),orderManagment.getById(r.getLong(COLUMN[4])),r.getDouble(COLUMN[2])));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (ItemDrafts.size()>0) return ItemDrafts;
        return null;
    }
}
