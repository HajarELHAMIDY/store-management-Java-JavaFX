package market.dao.payement;

import market.connection.DBConnection;
import market.dao.DAO;
import market.entities.payement.Draft;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DraftDAO implements DAO<Draft> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static DraftDAO draftDAO=null;
    static String TABLE="draft";
    static String COLUMN[]={"id","nbDraft","date_debut","rest"};
    private DraftDAO() {}

    public boolean creat(Draft d) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+") VALUES (?,?,?)";
        long id=dbConnection.executeUpdate(sql,d.getNbTraita(),d.getDate_debut(),d.getRest());
        d.setId(id);
        return false;
    }
    public static DraftDAO getInstance(){
        if (draftDAO==null) draftDAO=new DraftDAO();
        return draftDAO;
    }
    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Draft d){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,d.getNbTraita(),d.getDate_debut(),d.getRest(),d.getId());
        return false;
    }

    public Draft getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Draft(r.getLong(COLUMN[0]),r.getInt(COLUMN[1]),r.getDate(COLUMN[2]).toLocalDate(),r.getDouble(COLUMN[3]));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Draft> getAll(){
        List<Draft> draftList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                draftList.add(new Draft(r.getLong(COLUMN[0]),r.getInt(COLUMN[1]),r.getDate(COLUMN[2]).toLocalDate(),r.getDouble(COLUMN[3])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return draftList;
    }

    public List<Draft> getAll(String keyword) {
        return null;
    }
}
