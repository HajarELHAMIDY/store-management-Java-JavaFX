package market.entities;

public class Address {
    private long id;
    private String street;
    private int zipCode;
    private City city;

    public Address(long id, String street, int zipCode, City city) {
        this.id = id;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return street + " " +city +" "+city.getCountry()+" "+ zipCode;
    }
}
