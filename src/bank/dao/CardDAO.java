package bank.dao;

import bank.connection.DBConnection;
import bank.data.Card;
import bank.managment.AccountManagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements DAO<Card> {
    private static CardDAO cardDAO=null;
    private AccountManagment accountManagment=AccountManagment.getInstance();
    private DBConnection dbConnection=DBConnection.getConnection();
    static String TABLE="card";
    static String COLUMN[]={"id","num","type","cvv","creationDate","expirationMonth","expirationYear","idAccount"};
    private CardDAO(){ }
    public static CardDAO getInstance(){
        if (cardDAO==null) cardDAO=new CardDAO();
        return cardDAO;
    }
    @Override
    public boolean update(Card objet) {
        return false;
    }

    @Override
    public Card getByid(long id) {
        return null;
    }

    @Override
    public List<Card> getAll() {
        List<Card> cardList=new ArrayList();
        String sql="SELECT * FROM "+TABLE;
        try {
            ResultSet r=dbConnection.executeQuery(sql);
            while (r.next())
                cardList.add(new Card(r.getLong(COLUMN[0]),r.getString(COLUMN[1]),r.getString(COLUMN[2]),r.getString(COLUMN[3]),r.getDate(COLUMN[4]),r.getString(COLUMN[5]),r.getString(COLUMN[6]),accountManagment.getById(r.getLong(COLUMN[7]))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cardList.size()>0) return cardList;
        return null;
    }
}
