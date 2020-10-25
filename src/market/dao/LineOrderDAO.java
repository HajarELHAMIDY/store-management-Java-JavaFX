package market.dao;

import market.connection.DBConnection;
import market.entities.LineOrder;
import market.managment.CategoryManagment;
import market.managment.OrderManagment;
import market.managment.ProductManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineOrderDAO implements DAO<LineOrder> {
    private DBConnection dbConnection=DBConnection.getConnection();
    private static LineOrderDAO lineOrderDAO=null;
    private OrderManagment orderManagment=OrderManagment.getInstance();
    private ProductManagment productManagment=ProductManagment.getInstance();
    static String TABLE="lineOrder";
    static String COLUMN[]={"id","quantite","total","idProduct","idOrder"};

    private LineOrderDAO() {
    }
    public static LineOrderDAO getInstance(){
        if (lineOrderDAO==null)lineOrderDAO=new LineOrderDAO();
        return lineOrderDAO;
    }
    public boolean creat(LineOrder l) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+","+COLUMN[4]+") VALUES (?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,l.getQuantite(),l.getTotal(),l.getProduit().getId(),l.getOrder().getId());
        l.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(LineOrder l){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,l.getQuantite(),l.getTotal(),l.getProduit().getId(),l.getOrder().getId(),l.getId());
        return false;
    }

    public LineOrder getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new LineOrder(r.getLong(COLUMN[0]),productManagment.getById(r.getLong(COLUMN[3])),r.getInt(COLUMN[1]),orderManagment.getById(r.getLong(COLUMN[4])),r.getDouble(COLUMN[2]));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LineOrder> getAll(){
        List<LineOrder> lineOrders=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                lineOrders.add(new LineOrder(r.getInt(COLUMN[0]),productManagment.getById(r.getLong(COLUMN[3])),r.getInt(COLUMN[1]),orderManagment.getById(r.getLong(COLUMN[4])),r.getDouble(COLUMN[2])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineOrders;
    }

    public List<LineOrder> getAll(String keyword) {
        List<LineOrder> lineOrders=new ArrayList();
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[1]+" like '%?%'";
        try {
            ResultSet r=dbConnection.executeQuery(sql,keyword);
            while (r.next())
                lineOrders.add(new LineOrder(r.getInt(COLUMN[0]),productManagment.getById(r.getLong(COLUMN[3])),r.getInt(COLUMN[1]),orderManagment.getById(r.getLong(COLUMN[4])),r.getDouble(COLUMN[2])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (lineOrders.size()>0) return lineOrders;
        return null;
    }

}
