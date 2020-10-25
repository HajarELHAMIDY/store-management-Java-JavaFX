package market.dao;

import market.connection.DBConnection;
import market.entities.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO  {

    private DBConnection dbConnection = DBConnection.getConnection();

    private static final String TABLE = "country";
    private static final String[] FIELDS = {"id", "name"};
    private List<Country> countryList=null;
    private static CountryDAO countryDAO;
    private CountryDAO(){countryList=getAllDB();}
    public static CountryDAO getInstance(){
        if(countryDAO == null)
            countryDAO = new CountryDAO();
        return countryDAO;
    }

    public Country getByIdDB(long id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE id="+id;
        ResultSet res = dbConnection.executeQuery(sql);
        Country country = null;

        try {
            if(res.next()){
                country = new Country(res.getLong(FIELDS[0]), res.getString(FIELDS[1]));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }
    public List<Country> getAll(){
        return countryList;
    }
    public Country getById(long id){
        for (Country c:countryList){
            if (c.getId()==id)
                return c;
        }
        return null;
    }

    public List<Country> getAllDB() {
        String sql = "SELECT * FROM " + TABLE;
        ResultSet res = dbConnection.executeQuery(sql);
        List<Country> countries = new ArrayList<>();

        try {
            while (res.next()){
                Country country = new Country(res.getLong(FIELDS[0]), res.getString(FIELDS[1]));
                countries.add(country);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }


    public long add(Country country) {
        if(country != null) {
            String sql = "INSERT INTO " + TABLE + "("+FIELDS[1]+") " + "VALUES (?)";
                long id = dbConnection.executeUpdate(sql, country.getName());
                country.setId(id);
                return id;

        }

        return -1;
    }


    public void update(long id, Country country) {
        if(country != null){
            String sql = "UPDATE "+ TABLE +" SET " + ""+FIELDS[1]+"=? " + "WHERE "+FIELDS[0]+"=?";
                dbConnection.executeUpdate(sql, country.getName(),id);
        }
    }

    public void update(Country country) {
        if(country != null)
            update(country.getId(), country);
    }

    public void delete(long id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id=?";
            dbConnection.executeUpdate(sql, id);
    }

    public void delete(Country country) {
        if(country != null)
            delete(country.getId());
    }

//    public Collection<City> getCities(Country country){
//        if(country != null){
//            String sql = "SELECT * FROM " + CityDAO.TABLE_NAME + " WHERE " + CityDAO.FIELDS[2] + "="+country.getId();
//            ResultSet res = dbConnection.executeQuery(sql);
//            Collection<City> cities = new ArrayList<>();
//
//            try {
//                while (res.next()){
//                    City city = new City(res.getLong(CityDAO.FIELDS[0]), res.getString(CityDAO.FIELDS[1]), country);
//                    cities.add(city);
//                }
//                res.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return cities;
//        }
//
//        return null;
//    }

}
