package market.connection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Configuration {
    static String HOST="127.0.0.1";
    static String USER="root";
    static String PASS="";
    static String DB_NAME="java_gestion_magasin";
    static String PORT=":3306";
    static String URL="jdbc:mysql://"+HOST+""+PORT+"/"+DB_NAME;
    public static String numAccount="86161762366";
    public static boolean alertDeletAndUpdate(String titel,String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }
        return false;
    }
}
