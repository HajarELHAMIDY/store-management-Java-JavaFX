package market.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import market.connection.Configuration;
import market.dao.CityDAO;
import market.dao.CountryDAO;
import market.entities.City;
import market.entities.Client;
import market.entities.Country;
import market.managment.ClientManagment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable {
    @FXML
    TableView<Client> tableClient;
    @FXML
    TextField id;
    @FXML
    TextField nom;
    @FXML
    TextField prenom;
    @FXML
    TextField phone;
    @FXML
    TextField email;
    @FXML
    TextField address;
    @FXML
    ComboBox<Country> pays;
    @FXML
    ComboBox<City> villes;
    @FXML
    TextField zipcode;
    @FXML
    Label require;
    @FXML
    TextField search;


    ClientManagment clientManagment = ClientManagment.getInstance();
    CityDAO cityDAO=CityDAO.getInstance();
    CountryDAO countryDAO=CountryDAO.getInstance();
    ObservableList<Client> listClient= FXCollections.observableArrayList(clientManagment.getAll());
    ObservableList<Country> countries= FXCollections.observableArrayList(countryDAO.getAll());
    ObservableList<City> cities= FXCollections.observableArrayList();
    public void ajoutClient(){
        clientManagment.add(nom.getText(),prenom.getText(),phone.getText(),email.getText(),address.getText(),Integer.parseInt(zipcode.getText()),villes.getValue());
        resetForm();
        updateTable();
    }
    public void updateClient(){
        clientManagment.update(clientManagment.getById(Integer.parseInt(id.getText())),nom.getText(),prenom.getText(),phone.getText(),email.getText(),address.getText(),Integer.parseInt(zipcode.getText()),villes.getValue());
        resetForm();
        updateTable();
    }
    public void mouveToForm(){
        Client cl = tableClient.getSelectionModel().getSelectedItem();
        if (cl != null) {
            id.setText(Long.toString(cl.getId()));
            nom.setText(cl.getNom());
            prenom.setText(cl.getPrenom());
            phone.setText(cl.getPhone());
            email.setText(cl.getEmail());
            address.setText(cl.getAddress().getStreet());
            villes.getSelectionModel().select(cl.getAddress().getCity());
            pays.getSelectionModel().select(cl.getAddress().getCity().getCountry());
            zipcode.setText(Integer.toString(cl.getAddress().getZipCode()));
        }
    }
    public void changerCity(ActionEvent actionEvent){
        if (pays.getValue()!=null) {
            cities.setAll(pays.getValue().getCities());
            villes.setItems(cities);
        }
    }
    public void resetForm(){
        id.clear();
        nom.clear();
        prenom.clear();
        phone.clear();
        email.clear();
        address.clear();
        pays.getSelectionModel().clearSelection();
        villes.setItems(null);
        zipcode.clear();
    }
    public void deletClient(){
        if (Configuration.alertDeletAndUpdate("Delete Client","are you sure you want to delete this client") && !id.getText().isEmpty()) {
            clientManagment.delet(Integer.parseInt(id.getText()));
            updateTable();
        }
        resetForm();
    }
    private void updateTable(){
        listClient.setAll(clientManagment.getAll());
        tableClient.setItems(listClient);
    }
    private void updateTable(List<Client> cls){
        listClient.setAll(cls);
        tableClient.setItems(listClient);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setDisable(true);
        tableClient.setItems(listClient);
        pays.setItems(countries);
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Client> lists=new ArrayList();
                for (Client c:clientManagment.getAll()){
                    if (c.getNom().toLowerCase().contains(newValue.toLowerCase())||Long.toString(c.getId()).toLowerCase().contains(newValue.toLowerCase())||c.getPrenom().toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(c);
                    }
                }
                updateTable(lists);
            }
        });
    }
}
