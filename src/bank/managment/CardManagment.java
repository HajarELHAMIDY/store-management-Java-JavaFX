package bank.managment;

import bank.dao.AccountDAO;
import bank.dao.CardDAO;
import bank.data.Account;
import bank.data.Card;

import java.util.Date;
import java.util.List;

public class CardManagment {
    private List<Card> cardes;
    private CardDAO cardDAO=CardDAO.getInstance();
    private static CardManagment cardManagment=null;
    private CardManagment(){
        cardes=cardDAO.getAll();
    }
    public static CardManagment getInstance(){
        if (cardManagment==null) cardManagment=new CardManagment();
        return cardManagment;
    }


    public boolean update(Account user,String nom,String prenom){
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
    public List<Card> getAll(){
        return cardes;
    }
    public Card getById(long id){
        for (Card c:cardes){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }
    public Card getCard(String num,String cvv,String  expirationMonth,String expirationYear){
        for (Card c:cardes){
            if (c.getNumber().equals(num)&&c.getExpirationYear().equals(expirationYear)&&c.getExpirationMonth().equals(expirationMonth)&&c.getCvv().equals(cvv)){
                return c;
            }
        }
        return null;
    }

}
