package market.dao;

import market.connection.DBConnection;
import market.entities.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static CategoryDAO categoryDAO=null;
    static String TABLE="category";
    static String COLUMN[]={"id","nom","description"};

    private CategoryDAO() {}

    public boolean creat(Category c) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+") VALUES (?,?)";
        long id=dbConnection.executeUpdate(sql,c.getNom(),c.getDescription());
        c.setId(id);
        return false;
    }
    public static CategoryDAO getInstance(){
        if (categoryDAO==null) categoryDAO=new CategoryDAO();
        return categoryDAO;
    }
    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Category c){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,c.getNom(),c.getDescription(),c.getId());
        return false;
    }

    public Category getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Category(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAll(){
        List<Category> categoryList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                categoryList.add(new Category(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2])));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public List<Category> getAll(String keyword) {
        return null;
    }
}
