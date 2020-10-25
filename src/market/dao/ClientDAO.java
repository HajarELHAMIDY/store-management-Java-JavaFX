package market.dao;

import market.connection.DBConnection;
import market.entities.Address;
import market.entities.Client;
import market.managment.AddressManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client> {

    private DBConnection dbConnection=DBConnection.getConnection();
    private static ClientDAO clientDAO=null;
    private AddressManagment addressManagment=AddressManagment.getInstance();
    static String TABLE="client";
    static String COLUMN[]={"id","nom","prenom","phone","email","idAddress"};
    private ClientDAO(){}
    public static ClientDAO getInstance(){
        if (clientDAO==null) clientDAO=new ClientDAO();
        return clientDAO;
    }
    public boolean creat(Client c) {
        String sql = "INSERT INTO "+TABLE+" ("+COLUMN[1]+", "+COLUMN[2]+", "+COLUMN[3]+", "+COLUMN[4]+", "+COLUMN[5]+") VALUES (?,?,?,?,?)";
        long id=dbConnection.executeUpdate(sql,c.getNom(),c.getPrenom(),c.getPhone(),c.getEmail(),c.getAddress().getId());
        c.setId(id);
        return false;
    }

    public boolean delete(long id){
        String sql="DELETE FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,id);
        return false;
    }

    public boolean update(Client c){
        String sql="UPDATE "+TABLE+" SET "+COLUMN[1]+"=?,"+COLUMN[2]+"=?,"+COLUMN[3]+"=?,"+COLUMN[4]+"=?,"+COLUMN[5]+"=? WHERE "+COLUMN[0]+"=?";
        dbConnection.executeUpdate(sql,c.getNom(),c.getPrenom(),c.getPhone(),c.getEmail(),c.getAddress().getId(),c.getId());
        return false;
    }

    public Client getByid(long id){
        String sql="SELECT * FROM "+TABLE+" WHERE "+COLUMN[0]+"=?";

        try {
            ResultSet r=dbConnection.executeQuery(sql,id);
            if (r.next())
                return new Client(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getString(COLUMN[3]),r.getString(COLUMN[4]),addressManagment.getById(r.getLong(COLUMN[5])));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getAll(){
        List<Client> clienttList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                clienttList.add(new Client(r.getInt(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getString(COLUMN[3]),r.getString(COLUMN[4]),addressManagment.getById(r.getLong(COLUMN[5]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienttList;
    }

    public List<Client> getAll(String keyword) {
        return null;
    }
}

