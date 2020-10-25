package market.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import market.entities.payement.*;
import market.managment.payment.CashManagment;
import market.managment.payment.ClassicModeManagment;
import market.managment.payment.ItemDraftManagment;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerItemDraft implements Initializable {
    @FXML
    Label require;
    @FXML
    Button btnadd;
    @FXML
    Button btnupdate;
    @FXML
    Button btndelet;
    @FXML
    Button btnreset;
    @FXML
    Button btnshow;
    @FXML
    TextField id;
    @FXML
    TextField montant;
    @FXML
    DatePicker date_prevue;
    @FXML
    DatePicker date_effectue;
    @FXML
    ComboBox<String> paymentMode;
    private double restAPaye;
    private Stage paymentStage;
    private CashManagment cashManagment=CashManagment.getInstance();
    private ObservableList<String> listMode= FXCollections.observableArrayList(ClassicModeManagment.mode);
    private ItemDraftManagment itemDraftManagment=ItemDraftManagment.getInstance();
    private ClassicMode payementItem;
    private ControllerDraft controllerDraft;
    private ItemDraft itemDraft;
    public void addItem(ActionEvent actionEvent) {
        if (!paymentMode.getValue().isEmpty()) {
            switch (paymentMode.getValue()) {
                case "Espece":
                    payementItem = cashManagment.add();
                    break;
                case "Cheque":
                    loadCheck();
                    break;
            }
            if (payementItem != null && Double.parseDouble(montant.getText()) <= restAPaye) {
                itemDraft = new ItemDraft(-1, Double.parseDouble(montant.getText()), date_prevue.getValue(), date_effectue.getValue(), payementItem, null);
                controllerDraft.setItemDraft(itemDraft);
            } else {
                require.setText("Montant superieur Rest : " + restAPaye);
            }
        }
        initwindow();
    }

    private void loadCheck() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/check.fxml"));
            Parent root = null;
            root = loader.load();
            ControllerCheck ctrl = loader.getController();
            ctrl.setDataDraftItem(this,itemDraft);
            Scene newScene = new Scene(root);
            paymentStage = new Stage();
            paymentStage.initModality(Modality.APPLICATION_MODAL);
            paymentStage.setScene(newScene);
            paymentStage.getScene().getStylesheets().add("Mycss.css");
            paymentStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(ActionEvent actionEvent) {
    }

    public void deletItem(ActionEvent actionEvent) {
    }

    public void resetForm(ActionEvent actionEvent) {
    }
    public void setData(ControllerDraft controllerDraft, ItemDraft itemDraft,double restAPaye){
        this.controllerDraft=controllerDraft;
        this.itemDraft=itemDraft;
        this.restAPaye=restAPaye;
        initwindow();
    }
    private void initwindow() {
        if (this.itemDraft!=null){
            id.setText(Long.toString(itemDraft.getId()));
            montant.setText(Double.toString(itemDraft.getMontant()));
            date_effectue.setValue(itemDraft.getDate_effectue());
            date_prevue.setValue(itemDraft.getDate_prevue());
            switch (itemDraft.getItem().getClass().getSimpleName()){
                case "Check":paymentMode.getSelectionModel().select("Cheque");
                    break;
                case "Cash":paymentMode.getSelectionModel().select("Espece");
                    
                    break;
            }

            btnadd.setDisable(true);
            btnupdate.setDisable(true);
            btndelet.setDisable(true);
            btnreset.setDisable(true);
            btnshow.setDisable(false);
            paymentMode.setDisable(true);
        }
        montant.setText(Double.toString(restAPaye));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentMode.setItems(listMode);
        btnshow.setDisable(true);
        Date date=new Date();
        date_effectue.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    public void setPaymentItem(ClassicMode payementItem) {
        this.payementItem = payementItem;
        paymentStage.close();
    }
    public void showPaymentMode(ActionEvent actionEvent) {
        switch (paymentMode.getValue()){
            case "Espece":
                break;
            case "Cheque":loadCheck();
                break;
        }
    }
}
