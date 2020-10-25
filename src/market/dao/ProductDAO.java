package market.dao;

import market.connection.DBConnection;
import market.entities.Produit;
import market.managment.CategoryManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Produit> {
    private DBConnection dbConnection=DBConnection.getConnection();
    private CategoryManagment categorys = CategoryManagment.getInstance();
    private static ProductDAO productDAO=null;
    static String TABLE="produits";
    static String COLUMN[]={"id","designation","prixAchat","prixVente","quantite","category"};

    private ProductDAO() {
    }
    public static ProductDAO getInstance(){
        if (productDAO==null)productDAO=new ProductDAO();
        return productDAO;
    }
    public boolean creat(Produit p) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+","+COLUMN[3]+", "+COLUMN[4]+") VALUES (?,?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,p.getDesignation(),p.getPrixAchat(),p.getPrixVente(),p.getQuantite(),p.getCategory().getId());
        p.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Produit p){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=?,"+COLUMN[5]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,p.getDesignation(),p.getPrixAchat(),p.getPrixVente(),p.getQuantite(),p.getCategory().getId(),p.getId());
        return false;
    }

    public Produit getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        CategoryManagment categorys = CategoryManagment.getInstance();
        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Produit(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getDouble(COLUMN[2]),r.getDouble(COLUMN[3]),r.getInt(COLUMN[4]),categorys.getById(r.getInt(COLUMN[5])));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Produit> getAll(){
        List<Produit> productList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;

        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                productList.add(new Produit(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getDouble(COLUMN[2]),r.getDouble(COLUMN[3]),r.getInt(COLUMN[4]),categorys.getById(r.getInt(COLUMN[5]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Produit> getAll(String keyword) {
        List<Produit> productList=new ArrayList();
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[1]+" like '%?%'";
        try {
            ResultSet r=dbConnection.executeQuery(sql,keyword);
            while (r.next())
                productList.add(new Produit(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getDouble(COLUMN[2]),r.getDouble(COLUMN[3]),r.getInt(COLUMN[4]),categorys.getById(r.getInt(COLUMN[5]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (productList.size()>0) return productList;
        return null;
    }

}
