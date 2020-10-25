package market.entities;

public class City {
    private long id;
    private String name;
    private Country country;

    public City(long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.setCountry(country);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        if (this.country!=null)
            this.country.removeCity(this);
        this.country = country;
        if (this.country!=null)
            this.country.addCity(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
