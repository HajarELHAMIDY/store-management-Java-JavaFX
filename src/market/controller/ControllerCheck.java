package market.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import market.dao.payement.BankDAO;
import market.entities.payement.Bank;
import market.entities.payement.Check;
import market.entities.payement.ItemDraft;
import market.entities.payement.Payment;
import market.managment.payment.CheckManagment;
import market.managment.payment.PaymentModeManagment;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerCheck implements Initializable {
    @FXML
    TextField idCheck;
    @FXML
    TextField proprCheck;
    @FXML
    TextField numCheck;
    @FXML
    ComboBox<Bank> banckCheck;
    @FXML
    DatePicker dateCheck;
    @FXML
    Label require;
    @FXML
    Button btnadd;
    @FXML
    Button btnupdate;
    @FXML
    Button btndelet;
    @FXML
    Button btncancel;
    private ControllerPayment payment;
    private Payment pp;
    private CheckManagment checkManagment =CheckManagment.getInstance();
    private ControllerItemDraft controllerItemDraft;
    private BankDAO bankDAO=BankDAO.getInstance();
    private ObservableList<Bank> listBank= FXCollections.observableArrayList(bankDAO.getAll());
    private ItemDraft itemDraft;
    public void ajoutChech(ActionEvent actionEvent) {
        Check check =checkManagment.add(proprCheck.getText(),numCheck.getText(),banckCheck.getValue(),dateCheck.getValue());
        if (check!=null) {
            if (payment!=null) {
                payment.setPayementCreat(check);
            }
            else {
                if (controllerItemDraft!=null){
                    controllerItemDraft.setPaymentItem(check);
                }
            }

        }
        require.setText("Il faut remplaire tous les champ");
    }

    public void updateChech(ActionEvent actionEvent) {
    }

    public void deletChech(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void setData(ControllerPayment payment,Payment p) {
        this.payment = payment;
        this.pp=p;
        if (this.pp!=null){
            Check ch=(Check) this.pp.getPayementItem();
            iniWindow(ch);
        }

    }
    public void setDataDraftItem(ControllerItemDraft controllerItemDraft,ItemDraft itemDraft){
        this.controllerItemDraft=controllerItemDraft;
        this.itemDraft=itemDraft;
        if (this.itemDraft!=null){
            Check ch=(Check) this.itemDraft.getItem();
            iniWindow(ch);}
    }


    private void iniWindow(Check ch) {
        idCheck.setText(Long.toString(ch.getId()));
        dateCheck.setValue(ch.getDate_effet());
        proprCheck.setText(ch.getPropr());
        numCheck.setText(ch.getNum());
        //banckCheck.setText(ch.getBanck());
        banckCheck.getSelectionModel().select(ch.getBanck());
        idCheck.setDisable(true);
        dateCheck.setDisable(true);
        proprCheck.setDisable(true);
        numCheck.setDisable(true);
        banckCheck.setDisable(true);
        btnadd.setDisable(true);
        btnupdate.setDisable(true);
        btndelet.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        banckCheck.setItems(listBank);
        Date date = new Date();
        dateCheck.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
