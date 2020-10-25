package market.dao;

import market.connection.DBConnection;
import market.entities.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

    private DBConnection dbConnection = DBConnection.getConnection();
    CountryDAO countryDAO = CountryDAO.getInstance();
    private List<City> cities;
    private static final String TABLE = "city";
    private static final String[] FIELDS = {"id", "name", "country"};

    private static CityDAO cityDAO;
    private CityDAO(){cities=getAllDB();}
    public static CityDAO getInstance(){
        if(cityDAO == null)
            cityDAO = new CityDAO();
        return cityDAO;
    }


    public City getByIdDB(long id) {

        String sql = "SELECT * FROM " + TABLE + " WHERE id="+id;
        ResultSet res = dbConnection.executeQuery(sql);
        City city = null;
        try {
            if(res.next()){
                city = new City(res.getLong(FIELDS[0]), res.getString(FIELDS[1]), countryDAO.getById(res.getLong(FIELDS[2])));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }
    public List<City> getAll(){
            return cities;
    }
    public City getById(long id){
        for (City c:cities){
            if (c.getId()==id)
                return c;
        }
        return null;
    }
    public List<City> getAllDB() {
        CountryDAO countryDAO = CountryDAO.getInstance();
        String sql = "SELECT * FROM " + TABLE;
        ResultSet res = dbConnection.executeQuery(sql);
        List<City> cities = new ArrayList<>();

        try {
            while (res.next()){
                City city = new City(res.getLong(FIELDS[0]), res.getString(FIELDS[1]), countryDAO.getById(res.getLong(FIELDS[2])));
                cities.add(city);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }


    public long add(City city) {
        if(city != null) {
            String sql = "INSERT INTO " + TABLE + "("+FIELDS[1]+","+FIELDS[2]+") " + "VALUES (?,?)";
                long id = dbConnection.executeUpdate(sql, city.getName(),city.getCountry().getId());
                city.setId(id);
                return id;

        }

        return -1;
    }


    public void update(long id, City city) {
        if(city != null) {
            String sql = "UPDATE " + TABLE + " SET " + FIELDS[1] + "=?," + FIELDS[1] + "=? WHERE id=?";
            dbConnection.executeUpdate(sql, city.getName(), city.getCountry().getId(), id);
        }
    }

    public void update(City city) {
        if(city != null)
            update(city.getId(), city);
    }


    public void delete(long id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id=?";
            dbConnection.executeUpdate(sql, id);
    }


    public void delete(City city) {
        if(city != null)
            delete(city.getId());
    }

}