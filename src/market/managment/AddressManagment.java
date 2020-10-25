package market.managment;


import market.dao.AddressDAO;
import market.entities.Address;
import market.entities.City;

import java.util.List;

public class AddressManagment {

    private AddressDAO addressDAO = AddressDAO.getInstance();

    private List<Address> addresses;

    private static AddressManagment addressManagment=null;
    private AddressManagment(){
        addresses=addressDAO.getAll();
    }
    public static AddressManagment getInstance(){
        if (addressManagment==null) addressManagment=new AddressManagment();
        return addressManagment;
    }
    public Address add(String street, int zipCode,City city){
        if (street.length()<5 || zipCode<=0 ||city==null){
            return null;
        }
        Address address=new Address(-1,street,zipCode,city);
        addressDAO.creat(address);
        addresses.add(address);
        return address;

    }
    public boolean update(Address address,String street, int zipCode,City city){
        if (street.length()<5 || zipCode<=0 ||city==null){
            return false;
        }
        address.setStreet(street);
        address.setZipCode(zipCode);
        address.setCity(city);
        addressDAO.update(address);
        return true;
    }
    public boolean delet(long id){
        Address address=getById(id);
        if (address==null)
            return false;
        addressDAO.delete(id);
        addresses.remove(address);
        return true;
    }
    public List<Address> getAll(){
        if (addresses==null)
            addresses=addressDAO.getAll();
        return addresses;
    }
    public Address getById(long id){
        for (Address a:addresses){
            if (a.getId()==id){
                return a;
            }
        }
        return null;
    }

}