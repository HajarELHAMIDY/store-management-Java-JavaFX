package market.dao.payement;

import market.connection.DBConnection;
import market.dao.DAO;
import market.entities.payement.Cash;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashDAO implements DAO<Cash> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static CashDAO cashDAO=null;
    static String TABLE="cash";
    static String COLUMN[]={"id"};

    private CashDAO() {}

    public boolean creat(Cash c) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[0]+") VALUES (NULL)";
        long id=dbConnection.executeUpdate(sql);
        c.setId(id);
        return false;
    }
    public static CashDAO getInstance(){
        if (cashDAO==null) cashDAO=new CashDAO();
        return cashDAO;
    }
    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Cash c){
        String sql="UPDATE "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,c.getId());
        return false;
    }

    public Cash getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Cash(r.getInt(COLUMN[0]));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cash> getAll(){
        List<Cash> cashList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                cashList.add(new Cash(r.getInt(COLUMN[0])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashList;
    }

    public List<Cash> getAll(String keyword) {
        return null;
    }
}
