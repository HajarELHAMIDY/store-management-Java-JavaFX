package market.dao.payement;

import market.connection.DBConnection;
import market.dao.DAO;
import market.entities.payement.Online;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnlineDAO implements DAO<Online> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static OnlineDAO onlineDAO=null;
    static String TABLE="online";
    static String COLUMN[]={"id","numCart","annee_fin","mois_fin","cvv"};

    private OnlineDAO() {}

    public boolean creat(Online o) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+", "+COLUMN[4]+") VALUES (?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,o.getNumCart(),o.getAnnee_fin(),o.getMois_fin(),o.getCvv());
        o.setId(id);
        return false;
    }
    public static OnlineDAO getInstance(){
        if (onlineDAO==null) onlineDAO=new OnlineDAO();
        return onlineDAO;
    }
    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Online o){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,o.getNumCart(),o.getAnnee_fin(),o.getMois_fin(),o.getCvv(),o.getId());
        return false;
    }

    public Online getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Online(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getInt(COLUMN[2]),r.getInt(COLUMN[3]),r.getInt(COLUMN[4]));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Online> getAll(){
        List<Online> onlineList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                onlineList.add(new Online(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getInt(COLUMN[2]),r.getInt(COLUMN[3]),r.getInt(COLUMN[4])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return onlineList;
    }

    public List<Online> getAll(String keyword) {
        return null;
    }
}
