package market.dao;

import market.connection.DBConnection;
import market.entities.Order;
import market.managment.ClientManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static OrderDAO orderDAO=null;
    private ClientManagment clientManagment=ClientManagment.getInstance();
    static String TABLE="orders";
    static String COLUMN[]={"id","date","total","idClient","typeOrder"};

    private OrderDAO() {}

    public boolean creat(Order o) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+", "+COLUMN[4]+") VALUES (?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,o.getDate(),o.getTotal(),o.getClient().getId(),o.getShortOrderType());
        o.setId(id);
        return false;
    }
    public static OrderDAO getInstance(){
        if (orderDAO==null) orderDAO=new OrderDAO();
        return orderDAO;
    }
    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }


    public boolean update(Order o){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,o.getDate(),o.getTotal(),o.getClient().getId(),o.getShortOrderType(),o.getId());
        return false;
    }

    public Order getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Order(r.getInt(COLUMN[0]),r.getDate(COLUMN[1]).toLocalDate(),clientManagment.getById(r.getLong(COLUMN[3])),r.getDouble(COLUMN[2]),r.getShort(COLUMN[4]));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAll(){
        List<Order> orderList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next()) {
                orderList.add(new Order(r.getInt(COLUMN[0]), r.getDate(COLUMN[1]).toLocalDate(), clientManagment.getById(r.getLong(COLUMN[3])),r.getDouble(COLUMN[2]),r.getShort(COLUMN[4])));
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> getAll(String keyword) {
        return null;
    }
}
