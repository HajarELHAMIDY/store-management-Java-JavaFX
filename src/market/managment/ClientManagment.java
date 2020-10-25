package market.managment;


import market.dao.ClientDAO;
import market.entities.Address;
import market.entities.City;
import market.entities.Client;
import market.entities.Country;

import java.util.List;

public class ClientManagment {
    private List<Client> clients;
    private ClientDAO clientDAO= ClientDAO.getInstance();
    private static ClientManagment clientManagment=null;
    private AddressManagment addressManagment=AddressManagment.getInstance();
    private ClientManagment(){
        clients=clientDAO.getAll();
    }
    public static ClientManagment getInstance(){
        if (clientManagment==null) clientManagment=new ClientManagment();
        return clientManagment;
    }
    public boolean add(String nom, String prenom, String phone, String email, String address,int zipCode, City city){
        if (nom.length()<3 || prenom.length()<3){
            return false;
        }
        Address addressCliet=addressManagment.add(address,zipCode, city);
        Client client=new Client(-1,nom,prenom,phone,email,addressCliet);
        clientDAO.creat(client);
        clients.add(client);
        return true;

    }
    public boolean update(Client client,String nom, String prenom, String phone, String email, String address,int zipCode, City city){
        if (client==null ||nom.length()<5 || prenom.length()<10){
            return false;
        }
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setPhone(phone);
        client.getAddress().setCity(city);
        client.getAddress().setZipCode(zipCode);
        client.getAddress().setStreet(address);
        clientDAO.update(client);
        return true;
    }
    public boolean delet(long id){
        Client client=getById(id);
        if (client==null)
            return false;
        clientDAO.delete(id);
        clients.remove(client);
        return true;
    }
    public List<Client> getAll(){
        return clients;
    }
    public Client getById(long id){
        for (Client c:clients){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }
}
