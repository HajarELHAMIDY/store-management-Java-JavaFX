package market.dao.payement;

import market.connection.DBConnection;
import market.entities.payement.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    private DBConnection dbConnection = DBConnection.getConnection();

    private static final String TABLE = "bank";
    private static final String[] FIELDS = {"id", "name"};
    private List<Bank> bankList=null;
    private static BankDAO bankDAO;
    private BankDAO(){bankList=getAllDB();}
    public static BankDAO getInstance(){
        if(bankDAO == null)
            bankDAO = new BankDAO();
        return bankDAO;
    }

    public Bank getByIdDB(long id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE id="+id;
        ResultSet res = dbConnection.executeQuery(sql);
        Bank bank = null;

        try {
            if(res.next()){
                bank = new Bank(res.getLong(FIELDS[0]), res.getString(FIELDS[1]));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bank;
    }
    public List<Bank> getAll(){
        return bankList;
    }
    public Bank getById(long id){
        for (Bank b:bankList){
            if (b.getId()==id)
                return b;
        }
        return null;
    }

    public List<Bank> getAllDB() {
        String sql = "SELECT * FROM " + TABLE;
        ResultSet res = dbConnection.executeQuery(sql);
        List<Bank> banks = new ArrayList<>();

        try {
            while (res.next()){
                Bank bank = new Bank(res.getLong(FIELDS[0]), res.getString(FIELDS[1]));
                banks.add(bank);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return banks;
    }


    public long add(Bank bank) {
        if(bank != null) {
            String sql = "INSERT INTO " + TABLE + "("+FIELDS[1]+") " + "VALUES (?)";
            long id = dbConnection.executeUpdate(sql, bank.getName());
            bank.setId(id);
            return id;

        }

        return -1;
    }


    public void update(long id, Bank bank) {
        if(bank != null){
            String sql = "UPDATE "+ TABLE +" SET " + ""+FIELDS[1]+"=? " + "WHERE "+FIELDS[0]+"=?";
            dbConnection.executeUpdate(sql, bank.getName(),id);
        }
    }

    public void update(Bank bank) {
        if(bank != null)
            update(bank.getId(), bank);
    }

    public void delete(long id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id=?";
        dbConnection.executeUpdate(sql, id);
    }

    public void delete(Bank bank) {
        if(bank != null)
            delete(bank.getId());
    }
}
