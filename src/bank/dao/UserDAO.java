package bank.dao;

import bank.data.User;
import bank.connection.DBConnection;
import market.entities.Produit;
import market.managment.CategoryManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private DBConnection dbConnection=DBConnection.getConnection();
    static String TABLE="user";
    static String COLUMN[]={"id","nom","prenom","email"};
    private static UserDAO userDAO=null;
    private UserDAO(){}
    public static UserDAO getInstance(){
        if (userDAO==null) userDAO=new UserDAO();
        return userDAO;
    }
    @Override
    public boolean update(User objet) {
        return false;
    }

    @Override
    public User getByid(long id) {
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        CategoryManagment categorys = CategoryManagment.getInstance();
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new User(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getString(COLUMN[3]));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> userList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                userList.add(new User(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getString(COLUMN[3])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userList.size()>0) return userList;
        return null;
    }
}
