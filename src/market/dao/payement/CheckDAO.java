package market.dao.payement;

import market.connection.DBConnection;
import market.dao.DAO;
import market.entities.payement.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckDAO implements DAO<Check> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static CheckDAO checkDAO=null;
    private static BankDAO bankDAO=BankDAO.getInstance();
    static String TABLE="checkP";
    static String COLUMN[]={"id","propr","num","banck","date_effet"};

    private CheckDAO() {}

    public static CheckDAO getInstance(){
        if (checkDAO==null) checkDAO=new CheckDAO();
        return checkDAO;
    }
    public boolean creat(Check c) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+", "+COLUMN[4]+") VALUES (?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,c.getPropr(),c.getNum(),c.getBanck().getId(),c.getDate_effet());
        c.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Check c){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,c.getPropr(),c.getNum(),c.getBanck().getId(),c.getDate_effet(),c.getId());
        return false;
    }

    public Check getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Check(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),bankDAO.getById(Long.parseLong(r.getString(COLUMN[3]))),r.getDate(COLUMN[4]).toLocalDate());

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Check> getAll(){
        List<Check> checkList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                checkList.add(new Check(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),bankDAO.getById(Long.parseLong(r.getString(COLUMN[3]))),r.getDate(COLUMN[4]).toLocalDate()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkList;
    }

    public List<Check> getAll(String keyword) {
        return null;
    }
}
