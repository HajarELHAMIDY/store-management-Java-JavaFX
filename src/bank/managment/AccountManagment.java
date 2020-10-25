package bank.managment;

import bank.dao.AccountDAO;
import bank.data.Account;
import bank.data.Card;

import java.util.AbstractCollection;
import java.util.List;

public class AccountManagment {
    private List<Account> accounts;
    private AccountDAO accountDAO=AccountDAO.getInstance();
    private static AccountManagment accountManagment=null;
    private AccountManagment(){
        accounts=accountDAO.getAll();
    }
    public static AccountManagment getInstance(){
        if (accountManagment==null) accountManagment=new AccountManagment();
        return accountManagment;
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
    public List<Account> getAll(){
        return accounts;
    }
    public Account getByNum(String num){
        for (Account a:accounts){
            if (a.getNum().equals(num)){
                return a;
            }
        }
        return null;
    }
    public Account getById(long id){
        for (Account a:accounts){
            if (a.getId()==id){
                return a;
            }
        }
        return null;
    }
    public synchronized boolean  moneyTransfer(Card card, double cost, String numCompte) {
        System.out.println(card);

        Account account = card.getAccount();
        Account trasfaire=getByNum(numCompte);
        if(account.getSolde() >= cost){
            //account.setSolde(account.getSolde() - cost);
            //trasfaire.setSolde(trasfaire.getSolde()+cost);
            if (debit(account,cost)&&credit(trasfaire,cost)){
                accountDAO.update(account);
                accountDAO.update(trasfaire);
                return true;
            }

        }
        return false;
    }
    public boolean debit(Account debite, double cost) {

        if(debite!=null&&debite.getSolde() >= cost){
            debite.setSolde(debite.getSolde() - cost);
            return true;
        }
        return false;
    }
    public boolean credit(Account credite, double cost) {
        if (credite!=null) {
            credite.setSolde(credite.getSolde() + cost);
            return true;
        }
        return false;
    }
}
