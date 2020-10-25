package market.controller;

import market.dao.CategoryDAO;
import market.dao.ProductDAO;
import market.entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    ProductDAO productDAO=ProductDAO.getInstance();
    CategoryDAO categoryDAO = CategoryDAO.getInstance();
    @FXML
    TextField idproduit;
    @FXML
    TextField nomproduit;
    @FXML
    TextField prixAchatproduit;
    @FXML
    TextField prixVenteproduit;
    @FXML
    Label require;
    @FXML
    TableView<Produit> tableproduit;
    ObservableList<Produit> produits = FXCollections.observableArrayList();
    public void addproduit(){
        if (idproduit.getText().trim().isEmpty() || nomproduit.getText().trim().isEmpty() || prixAchatproduit.getText().trim().isEmpty() ){
            require.setText("tous les champs obligatoires");
        }
        else {
            //Produit p=new Produit(Integer.parseInt(idproduit.getText()),nomproduit.getText(),Double.parseDouble(prixAchatproduit.getText()),Double.parseDouble(prixVenteproduit.getText()),categoryDAO.getByid(0));
            //productDAO.creat(p);
            System.out.println(idproduit.getText()+"1"+nomproduit.getText()+"1"+prixAchatproduit.getText());
            idproduit.setText("");
            nomproduit.setText("");
            prixVenteproduit.setText("");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //produits.add();
        //tableproduit.getItems().add(new Produit(1,"imad",10,100,1));
    }
}
