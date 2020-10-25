package market.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import market.connection.Configuration;
import market.entities.Category;
import market.managment.CategoryManagment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerCategory implements Initializable {
    @FXML
    TableView<Category> tablecategory;
    @FXML
    TextField idcategory;
    @FXML
    TextField nomcategory;
    @FXML
    TextField desccategory;
    @FXML
    Label require;
    @FXML
    TextField serch;

    CategoryManagment categoryManagment= CategoryManagment.getInstance();
    ObservableList<Category> listcategory= FXCollections.observableArrayList(categoryManagment.getAll());

    public void ajoutCategory(){
        if (nomcategory.getText().isEmpty()||desccategory.getText().isEmpty()){
            require.setText("Tous les champ et obligatoire");
            return;
        }
        categoryManagment.add(nomcategory.getText(),desccategory.getText());
            resetForm();
            updateTable();
    }
    public void updateCategory(){
        if (idcategory.getText().isEmpty()||nomcategory.getText().isEmpty()||desccategory.getText().isEmpty()){
            require.setText("il faut selectionne une category pour modifier");
            return;
        }
        if (Configuration.alertDeletAndUpdate("update category","voullez vous vraiment modifier cette category")){
            categoryManagment.update(categoryManagment.getById(Integer.parseInt(idcategory.getText())),nomcategory.getText(),desccategory.getText());
            resetForm();
            updateTable();
        }
    }
    public void mouveToForm(){
        Category cat = tablecategory.getSelectionModel().getSelectedItem();
        if (cat != null) {
            idcategory.setText(Long.toString(cat.getId()));
            nomcategory.setText(cat.getNom());
            desccategory.setText(cat.getDescription());
        }
    }
    public void resetForm(){
        idcategory.setText("");
        nomcategory.setText("");
        desccategory.setText("");
    }
    public void deletCategory(){
        if (idcategory.getText().isEmpty()){
            require.setText("il faut selectionne une category pour Supprimer");
            return;
        }
        if (Configuration.alertDeletAndUpdate("delet category","voullez vous vraiment supprimer cette category")){
            categoryManagment.delet(Integer.parseInt(idcategory.getText()));
            resetForm();
            updateTable();
        }
    }

    private void updateTable(){
        listcategory.setAll(categoryManagment.getAll());
        tablecategory.setItems(listcategory);
    }
    private void updateTable(List<Category> catrs){
        listcategory.setAll(catrs);
        tablecategory.setItems(listcategory);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idcategory.setDisable(true);
        tablecategory.getItems().addAll(listcategory);
        serch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Category> lists=new ArrayList();
                for (Category c:categoryManagment.getAll()){
                    if (c.getNom().toLowerCase().contains(newValue.toLowerCase())||Long.toString(c.getId()).toLowerCase().contains(newValue.toLowerCase())||c.getDescription().toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(c);
                    }
                }
                updateTable(lists);
            }
        });
    }
}
