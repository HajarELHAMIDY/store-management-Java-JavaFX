package bank.connection;

public class Configuration {
    static String HOST="127.0.0.1";
    static String USER="root";
    static String PASS="";
    static String DB_NAME="java_gestion_magasin_bank";
    static String PORT=":3306";
    static String URL="jdbc:mysql://"+HOST+""+PORT+"/"+DB_NAME;
}
