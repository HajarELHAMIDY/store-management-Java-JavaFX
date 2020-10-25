package market.connection;

import java.sql.*;
import java.time.LocalDate;
import java.sql.Date;

public class DBConnection {
    private Connection connection;
    static DBConnection dbconnection;

    private DBConnection(){
        try {
            connection= DriverManager.getConnection(Configuration.URL,Configuration.USER,Configuration.PASS);
            System.out.println("connecxion fait");
        }catch (SQLException e){
            System.out.println("problemm connecxion");
            e.printStackTrace();
        }
    }

    public static DBConnection getConnection() {
            if (dbconnection==null) dbconnection=new DBConnection();
            return dbconnection;
    }
    private void addParametreToPSR(PreparedStatement prs,Object ...parameter) throws SQLException {
        int i=1;
        for (Object obj:parameter){
                switch (obj.getClass().getSimpleName()) {
                    case "Integer":
                        prs.setInt(i, (Integer)obj);
                        break;
                    case "Double":
                        prs.setDouble(i, (Double)obj);
                        break;
                    case "String":
                        prs.setString(i, (String) obj);
                        break;
                    case "Long":
                        prs.setLong(i, (Long) obj);
                        break;
                    case "LocalDate":
                        prs.setDate(i, Date.valueOf((LocalDate) obj));
                        break;
                    case "Short":
                        prs.setShort(i, (Short) obj);
                        break;
                }

            i++;
        }
    }
    public long executeUpdate(String sql, Object ...parameter){
        PreparedStatement prs;
            try {
                prs=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                addParametreToPSR(prs,parameter);
                if (prs.executeUpdate()==1){
                    ResultSet r=prs.getGeneratedKeys();
                    if (r.next())
                        return r.getLong(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return -1;
    }

    public ResultSet executeQuery(String sql,Object ...parametre){
        try {
            PreparedStatement prs=connection.prepareStatement(sql);
            addParametreToPSR(prs,parametre);
            return prs.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
