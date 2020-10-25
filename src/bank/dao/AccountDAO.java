package bank.dao;

import bank.data.Account;
import bank.connection.DBConnection;
import bank.managment.UserManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements DAO<Account> {
    private static AccountDAO accountDAO=null;
    private UserManagment userManagment=UserManagment.getInstance();
    private DBConnection dbConnection=DBConnection.getConnection();
    static String TABLE="account";
    static String COLUMN[]={"id","num","rip","creationDate","solde","idUser"};
    private AccountDAO(){ }
    public static AccountDAO getInstance(){
        if (accountDAO==null) accountDAO=new AccountDAO();
        return accountDAO;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }


    public boolean update(Account u){
        //id num rip creationDate solde idUser
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=?,"+COLUMN[5]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,u.getNum(),u.getRIB(),u.getCreationDate(),u.getSolde(),u.getUser().getId(),u.getId());
        return false;
    }

    public Account getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Account(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getDate(COLUMN[3]),r.getDouble(COLUMN[4]),userManagment.getById(r.getInt(COLUMN[5])));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAll(){
        List<Account> accountList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                accountList.add(new Account(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getDate(COLUMN[3]),r.getDouble(COLUMN[4]),userManagment.getById(r.getInt(COLUMN[5]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (accountList.size()>0) return accountList;
        return null;

    }

}
