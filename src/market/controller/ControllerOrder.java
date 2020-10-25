package market.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import market.entities.Client;
import market.entities.Order;
import market.entities.OrderType;
import market.managment.ClientManagment;
import market.managment.LineOrderManagment;
import market.managment.OrderManagment;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerOrder implements Initializable {
    @FXML
    Button btnCancel;
    @FXML
    TableView<Order> tableOrder;
    @FXML
    TextField idOrder;
    @FXML
    DatePicker dateOrder;
    @FXML
    ChoiceBox<Client> clientOrder;
    @FXML
    TextField totalOrder;
    @FXML
    Label require;
    @FXML
    Button btnPayer;
    @FXML
    TextField serch;
    Date date=new Date();
    OrderManagment orderManagment= OrderManagment.getInstance();
    ClientManagment clientManagment=ClientManagment.getInstance();
    LineOrderManagment lineOrderManagment=LineOrderManagment.getInstance();
    ObservableList<Order> listOrder= FXCollections.observableArrayList(orderManagment.getAll());
    ObservableList<Client> clients= FXCollections.observableArrayList(clientManagment.getAll());
    public void ajoutOrder(){
        orderManagment.add(dateOrder.getValue(),clientOrder.getValue());
        resetForm();
        updateTable();
    }
    public void updateOrder(){
        orderManagment.update(orderManagment.getById(Integer.parseInt(idOrder.getText())),dateOrder.getValue(),clientOrder.getValue());
        resetForm();
        updateTable();
    }
    public void mouveToForm(MouseEvent mouseEvent){
        Order order = tableOrder.getSelectionModel().getSelectedItem();
        if (order != null) {
            idOrder.setText(Long.toString(order.getId()));
            dateOrder.setValue(order.getDate());
            totalOrder.setText(Double.toString(order.getTotal()));
            clientOrder.getSelectionModel().select(order.getClient());
            if (order.getPayement() == null) {
                btnPayer.setVisible(true);
            }
            if (!(order.getShortOrderType()== OrderType.New)){
                btnCancel.setDisable(true);
            }
            if (mouseEvent.getClickCount() >= 2) {
                showLignes(order);
            }
        }
    }
    @FXML
    public void showLignes(Order o) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/ligneOrder.fxml"));
            Parent root = null;
            root = (Parent) loader.load();
            ControllerLigneOrder ctrl = loader.getController();
            ctrl.setOrder(o,this);
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setScene(newScene);
            newStage.getScene().getStylesheets().add("Mycss.css");
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resetForm(){
        idOrder.setText("");
        totalOrder.setText("");
        clientOrder.getSelectionModel().select(0);
        dateOrder.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        btnPayer.setVisible(true);
    }
    public void deletOrder(){
        orderManagment.delet(Integer.parseInt(idOrder.getText()));
        resetForm();
        updateTable();
    }

    public void updateTable(){
        listOrder.setAll(orderManagment.getAll());
        tableOrder.setItems(listOrder);
    }
    private void updateTable(List<Order> orders){
        listOrder.setAll(orders);
        tableOrder.setItems(listOrder);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idOrder.setDisable(true);
        totalOrder.setDisable(true);
        clientOrder.setItems(clients);
        tableOrder.getItems().addAll(listOrder);
        clientOrder.getSelectionModel().select(0);
        dateOrder.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        serch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Order> lists=new ArrayList();
                for (Order o:orderManagment.getAll()){
                    if (o.getClient().toString().toLowerCase().contains(newValue.toLowerCase())||Long.toString(o.getId()).toLowerCase().contains(newValue.toLowerCase())||o.getDate().toString().toLowerCase().contains(newValue.toLowerCase())||Double.toString(o.getTotal()).toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(o);
                    }
                }
                updateTable(lists);
            }
        });
    }

    public void payerOrder(ActionEvent actionEvent) {
        if (!idOrder.getText().isEmpty()) {
            Order order = orderManagment.getById(Long.parseLong(idOrder.getText()));
            if (order != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/payment.fxml"));
                    Parent root = null;
                    root = (Parent) loader.load();
                    ControllerPayment ctrl = loader.getController();
                    ctrl.setData(this, order);
                    Scene newScene = new Scene(root);
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    newStage.setScene(newScene);
                    newStage.getScene().getStylesheets().add("Mycss.css");
                    newStage.showAndWait();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else require.setText("il faut Selectione une Commande");
    }

    public void cancelOrder(ActionEvent actionEvent) {
        if (!idOrder.getText().isEmpty()) {
            Order order = orderManagment.getById(Long.parseLong(idOrder.getText()));
            if (order != null) {
                orderManagment.cancelOrder(order);
            }
        }
    }
}
