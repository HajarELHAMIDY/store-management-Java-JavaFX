package bank.managment;



import bank.dao.UserDAO;
import bank.data.User;

import java.util.List;

public class UserManagment {
    private List<User> users;
    private UserDAO userDAO= UserDAO.getInstance();
    private static UserManagment userManagment=null;
    private UserManagment(){
        users=userDAO.getAll();
    }
    public static UserManagment getInstance(){
        if (userManagment==null) userManagment=new UserManagment();
        return userManagment;
    }

    public boolean update(User user,String nom,String prenom){
//        if (client==null ||nom.length()<5 || prenom.length()<10){
//            return false;
//        }
//        client.setNom(nom);
//        client.setPrenom(prenom);
//        clientDAO.update(client);
        return true;
    }
    public boolean delet(long id){
//        Client client=getById(id);
//        if (client==null)
//            return false;
//        clientDAO.delete(id);
//        clients.remove(client);
        return true;
    }
    public List<User> getAll(){
        return users;
    }
    public User getById(long id){
        for (User u:users){
            if (u.getId()==id){
                return u;
            }
        }
        return null;
    }
}
