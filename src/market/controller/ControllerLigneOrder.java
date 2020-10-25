package market.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import market.entities.*;
import market.managment.CategoryManagment;
import market.managment.LineOrderManagment;
import market.managment.ProductManagment;

import java.io.IOException;


public class ControllerLigneOrder {
    private Order order;

    @FXML
    TableView<LineOrder> tableLignes;
    @FXML
    TextField id;
    @FXML
    ComboBox<Produit> produits;
    @FXML
    ComboBox<Category> categorys;
    @FXML
    TextField quantite;
    @FXML
    TextField total;
    @FXML
    Button btnAjoute;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnDelet;
    ControllerOrder c;
    LineOrderManagment lineOrderManagment=LineOrderManagment.getInstance();
    ObservableList<LineOrder> listLigneOrder= FXCollections.observableArrayList();
    ProductManagment productManagment=ProductManagment.getInstance();
    CategoryManagment categoryManagment=CategoryManagment.getInstance();
    ObservableList<Produit> listProduits= FXCollections.observableArrayList();
    ObservableList<Category> listCategory= FXCollections.observableArrayList();

    public ControllerLigneOrder() throws IOException {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order,ControllerOrder c) {
        this.order = order;
        this.c=c;
        initWindow();
    }

    public void ajoutOrder(ActionEvent actionEvent) {
        lineOrderManagment.add(produits.getValue(),Integer.parseInt(quantite.getText()),order);
        updateTable();
        resetForm();

    }

    public void updateOrder(ActionEvent actionEvent) {
        lineOrderManagment.update(lineOrderManagment.getById(Long.parseLong(id.getText())),produits.getValue(),Integer.parseInt(quantite.getText()),order);
        updateTable();
        resetForm();
    }

    public void deletOrder(ActionEvent actionEvent) {
        lineOrderManagment.delet(Long.parseLong(id.getText()));
        updateTable();
        resetForm();
    }

    public void resetForm() {
        id.clear();
        quantite.clear();
        categorys.getSelectionModel().clearSelection();
        produits.setItems(null);
        total.clear();

    }


    public void initWindow(){
        updateTable();
        id.setDisable(true);
        total.setDisable(true);
        listCategory.setAll(categoryManagment.getAll());
        if (order.getShortOrderType()== OrderType.New){
            btnAjoute.setDisable(false);
            btnDelet.setDisable(false);
            btnUpdate.setDisable(false);
        }
        categorys.setItems(listCategory);
        quantite.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (produits.getValue()!=null&& !newValue.isEmpty())
                    total.setText(Double.toString(produits.getValue().getPrixVente()*Integer.parseInt(newValue)));
            }
        });
    }
    private void updateTable(){
        listLigneOrder.setAll(order.getLineOrders());
        tableLignes.setItems(listLigneOrder);
        c.updateTable();
    }
    public void updatetotal(ActionEvent actionEvent) {
        if (produits.getValue()!=null && !quantite.getText().isEmpty())
            total.setText(Double.toString(produits.getValue().getPrixVente()*Integer.parseInt(quantite.getText())));
    }

    public void mouveToForm(MouseEvent mouseEvent) {
        LineOrder ligne = tableLignes.getSelectionModel().getSelectedItem();

        if (ligne != null) {
            id.setText(Long.toString(ligne.getId()));
            total.setText(Double.toString(ligne.getTotal()));
            quantite.setText(Integer.toString(ligne.getQuantite()));
            categorys.getSelectionModel().select(ligne.getProduit().getCategory());
            listProduits.setAll(ligne.getProduit().getCategory().getProduits());
            produits.setItems(listProduits);
            produits.getSelectionModel().select(ligne.getProduit());
        }
    }

    public void updataProduit(ActionEvent actionEvent) {
        if (categorys.getValue()!=null){
            listProduits.setAll(categorys.getValue().getProduits());
            produits.setItems(listProduits);
        }
    }
}
