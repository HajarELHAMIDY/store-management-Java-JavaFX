package market.entities;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private long id;
    private List<City> cities;
    private String name;

    public Country(long id,String name) {
        this.id = id;
        this.name = name;
        this.cities=new ArrayList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }
    public void addCity(City city){
        cities.add(city);
    }
    public void removeCity(City city){
        cities.remove(city);
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
