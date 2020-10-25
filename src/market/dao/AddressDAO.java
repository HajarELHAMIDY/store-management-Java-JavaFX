package market.dao;

import market.connection.DBConnection;
import market.entities.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements DAO<Address> {

    private DBConnection dbConnection = DBConnection.getConnection();

    public static final String TABLE_NAME = "address";
    public static final String[] FIELDS = {"id", "street","postalcode", "city"};

    private static AddressDAO addressDAO;
    private AddressDAO(){}
    public static AddressDAO getInstance(){
        if(addressDAO == null)
            addressDAO = new AddressDAO();
        return addressDAO;
    }

    @Override
    public Address getByid(long id) {
        CountryDAO countryDAO = CountryDAO.getInstance();
        CityDAO cityDAO = CityDAO.getInstance();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id="+id;
        ResultSet res = dbConnection.executeQuery(sql);
        Address address = null;

        try {
            if(res.next()){
                address = new Address(res.getLong(FIELDS[0]), res.getString(FIELDS[1]),res.getInt(FIELDS[2]), cityDAO.getById(res.getLong(FIELDS[3])));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public List<Address> getAll() {
        CountryDAO countryDAO = CountryDAO.getInstance();
        CityDAO cityDAO = CityDAO.getInstance();
        String sql = "SELECT * FROM " + TABLE_NAME;
        ResultSet res = dbConnection.executeQuery(sql);
        List<Address> addresses = new ArrayList<>();

        try {
            while (res.next()){
                Address address = new Address(res.getLong(FIELDS[0]), res.getString(FIELDS[1]),res.getInt(FIELDS[2]), cityDAO.getById(res.getLong(FIELDS[3])));
                addresses.add(address);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    @Override
    public List<Address> getAll(String keyword) {
        return null;
    }

    @Override
    public boolean creat(Address address) {
        if(address != null) {
            String sql = "INSERT INTO " + TABLE_NAME + "("+FIELDS[1]+","+FIELDS[2]+","+FIELDS[3]+") " + "VALUES (?,?,?)";
                long id = dbConnection.executeUpdate(sql, address.getStreet(),address.getZipCode(),address.getCity().getId());
                address.setId(id);
                return true;
        }
        return false;
    }


    public boolean update(long id, Address address) {
        if(address != null){
            String sql = "UPDATE "+ TABLE_NAME +" SET "+FIELDS[1]+"=?,"+FIELDS[2]+"=?,"+FIELDS[3]+"=? WHERE id=?";

                dbConnection.executeUpdate(sql, address.getStreet(),address.getZipCode(),address.getCity().getId(),id);
        }
        return false;
    }

    public boolean update(Address address) {
        if(address != null)
            update(address.getId(), address);
        return false;
    }



    public boolean delete(long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id="+id;
            dbConnection.executeUpdate(sql, false);
        return false;
    }


    public void delete(Address address) {
        if(address != null)
            delete(address.getId());
    }

}
