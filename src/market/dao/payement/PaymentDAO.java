package market.dao.payement;

import market.connection.DBConnection;

import market.dao.DAO;
import market.entities.payement.Payment;
import market.managment.OrderManagment;
import market.managment.payment.PaymentModeManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements DAO<Payment> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static PaymentDAO paymentDAO=null;
    private PaymentModeManagment paymentModeManagment=PaymentModeManagment.getInstance();
    private OrderManagment orderManagment=OrderManagment.getInstance();
    static String TABLE="payment";
    static String COLUMN[]={"id","date","idOrder","idMode","mode"};

    private PaymentDAO() {}
    public static PaymentDAO getInstance(){
        if (paymentDAO==null) paymentDAO=new PaymentDAO();
        return paymentDAO;
    }
    public boolean creat(Payment p) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+", "+COLUMN[4]+") VALUES (?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,p.getDatePayement(),p.getOrder().getId(),p.getPayementItem().getId(),p.getPayementItem().getClass().getSimpleName());
        p.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Payment p){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,p.getDatePayement(),p.getOrder().getId(),p.getPayementItem().getId(),p.getPayementItem().getClass().getSimpleName());
        return false;
    }

    public Payment getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Payment(r.getLong(COLUMN[0]),r.getDate(COLUMN[1]).toLocalDate(),orderManagment.getById(r.getLong(COLUMN[2])),paymentModeManagment.getById(r.getLong(COLUMN[3]),r.getString(COLUMN[4])));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Payment> getAll(){
        List<Payment> paymentList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                paymentList.add(new Payment(r.getLong(COLUMN[0]),r.getDate(COLUMN[1]).toLocalDate(),orderManagment.getById(r.getLong(COLUMN[2])),paymentModeManagment.getById(r.getLong(COLUMN[3]),r.getString(COLUMN[4]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    public List<Payment> getAll(String keyword) {
        return null;
    }
}
