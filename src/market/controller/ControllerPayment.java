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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import market.entities.Order;
import market.entities.payement.PayementMode;
import market.managment.payment.CashManagment;
import market.managment.payment.PaymentManagment;
import market.managment.payment.PaymentModeManagment;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerPayment implements Initializable {
    @FXML
    Label require;
    @FXML
    TextField id;
    @FXML
    DatePicker paymentDate;
    @FXML
    ComboBox<String> paymentMode;
    @FXML
    TextField total;
    @FXML
    Button btnadd;
    @FXML
    Button btnupdate;
    @FXML
    Button btndelet;
    @FXML
    Button btnshow;
    @FXML
    Button btnreset;
    private Stage paymentStage;
    private PaymentManagment paymentManagment=PaymentManagment.getInstance();
    private CashManagment cashManagment=CashManagment.getInstance();
    private ObservableList<String> listMode= FXCollections.observableArrayList(PaymentModeManagment.mode);
    private Order order;
    private PayementMode payementCreat;
    private ControllerOrder controllerOrder;
    public PayementMode getPayementCreat() {
        return payementCreat;
    }

    public void setPayementCreat(PayementMode payementCreat) {
        this.payementCreat = payementCreat;
        paymentStage.close();
    }

    public void ajoutePayment(ActionEvent actionEvent) {
        switch (paymentMode.getValue()){
            case "Espece":payementCreat=cashManagment.add();
                break;
            case "Cheque":loadCheck();
                break;
            case "Carte":loadOnline();
                break;
            case "Traite":loadDraft();
                break;
        }
        if (payementCreat!=null&&paymentDate.getValue()!=null) {
            paymentManagment.add(paymentDate.getValue(), order, payementCreat);
            payementCreat=null;
            controllerOrder.updateTable();
            resetForm();
            initwindow();
            return;
        }
        require.setText("il faut termine tous le process pour cree payment");
    }

    public void updatePayment(ActionEvent actionEvent) {
    }

    public void deletPayment(ActionEvent actionEvent) {
    }

    public void resetForm() {
        id.clear();
        paymentDate.getEditor().clear();
        paymentMode.getSelectionModel().clearSelection();

    }

    public void mouveToForm(MouseEvent mouseEvent) {
    }
    private void loadCheck(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/check.fxml"));
            Parent root = null;
            root = (Parent) loader.load();
            ControllerCheck ctrl = loader.getController();
            ctrl.setData(this,order.getPayement());
            initWindowModePayment(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void loadDraft(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/draft.fxml"));
            Parent root = null;
            root = (Parent) loader.load();
            ControllerDraft ctrl = loader.getController();
            ctrl.setData(this,order.getPayement(),order.getTotal());
            initWindowModePayment(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void loadOnline(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/online.fxml"));
            Parent root = null;
            root = (Parent) loader.load();
            ControllerOnline ctrl = loader.getController();
            ctrl.setData(this,order.getTotal(),order.getPayement());
            initWindowModePayment(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initWindowModePayment(Parent root){
        Scene newScene = new Scene(root);
        paymentStage = new Stage();
        paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.setScene(newScene);
        paymentStage.getScene().getStylesheets().add("Mycss.css");
        paymentStage.showAndWait();
    }
    public void setData(ControllerOrder controllerOrder,Order order){
        this.controllerOrder=controllerOrder;
        this.order=order;
        total.setText(Double.toString(order.getTotal()));
        initwindow();
    }

    private void initwindow() {
        if (this.order.getPayement()!=null){
            id.setText(Long.toString(this.order.getPayement().getId()));
            paymentDate.setValue(this.order.getPayement().getDatePayement());
            switch (this.order.getPayement().getPayementItem().getClass().getSimpleName()){
                case "Check":paymentMode.getSelectionModel().select("Cheque");
                    break;
                case "Cash":paymentMode.getSelectionModel().select("Espece");
                    break;
                case "Online":paymentMode.getSelectionModel().select("Carte");
                    break;
                case "Draft":paymentMode.getSelectionModel().select("Traite");
                    break;
            }

            btnadd.setDisable(true);
            btnupdate.setDisable(true);
            btndelet.setDisable(true);
            btnreset.setDisable(true);
            btnshow.setDisable(false);
            paymentMode.setDisable(true);
            paymentDate.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentMode.setItems(listMode);
        Date date = new Date();
        paymentDate.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public void showPaymentMode(ActionEvent actionEvent) {
        switch (paymentMode.getValue()){
            case "Espece":
                break;
            case "Cheque":loadCheck();
                break;
            case "Carte":loadOnline();
                break;
            case "Traite":loadDraft();
                break;
        }
    }
}
