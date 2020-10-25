package market.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import market.entities.payement.Draft;
import market.entities.payement.ItemDraft;
import market.entities.payement.Payment;
import market.managment.payment.DraftManagment;
import market.managment.payment.ItemDraftManagment;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerDraft implements Initializable {
    @FXML
    Button btnAddItem;
    @FXML
    TableView<ItemDraft> tableItemDraft;
    @FXML
    Button btnAjoute;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnDelet;
    @FXML
    TextField idDraft;
    @FXML
    TextField nbDraft;
    @FXML
    DatePicker dateDraft;
    @FXML
    TextField restDraft;
    @FXML
    TextField total;

    private Double totalApayer;
    private ControllerPayment paymentControler;
    private Payment payment;
    private ItemDraft itemDraft;
    private Stage newStage;
    private ItemDraftManagment itemDraftManagment=ItemDraftManagment.getInstance();
    private DraftManagment draftManagment=DraftManagment.getInstance();
    ObservableList<ItemDraft> listItemDraft= FXCollections.observableArrayList();
    public void ajoutDraft(ActionEvent actionEvent) {
        showItem(null);
        if (itemDraft!=null){
            Draft draft=draftManagment.add(Integer.parseInt(nbDraft.getText()),dateDraft.getValue(),totalApayer-itemDraft.getMontant());
            if (draft!=null) {
                itemDraftManagment.add(draft, itemDraft);
                paymentControler.setPayementCreat(draft);
            }
        }
    }

    public void updateDraft(ActionEvent actionEvent) {
    }

    public void deletDraft(ActionEvent actionEvent) {
    }

    public void resetForm() {
    }
    public void setData(ControllerPayment payment, Payment p,double total) {
        this.paymentControler = payment;
        this.payment=p;
        this.totalApayer=total;
        this.total.setText(Double.toString(total));
        iniWindow();
    }
    private void iniWindow() {
        if (this.payment!=null){
            Draft draft=(Draft) this.payment.getPayementItem();
            idDraft.setText(Long.toString(draft.getId()));
            dateDraft.setValue(draft.getDate_debut());
            nbDraft.setText(Integer.toString(draft.getNbTraita()));
            restDraft.setText(Double.toString(draft.getRest()));
            //total.setText(Double.toString(payment.getOrder().getTotal()));
            updateTable();
            if (draft.getRest()<=0)
                btnAddItem.setDisable(true);
            idDraft.setDisable(true);
            dateDraft.setDisable(true);
            nbDraft.setDisable(true);
            restDraft.setDisable(true);
            btnAjoute.setDisable(true);
            btnDelet.setDisable(true);
            btnUpdate.setDisable(true);
        }else {
            restDraft.setText(Double.toString(totalApayer));
        }

    }

    public void showItemDraft(MouseEvent mouseEvent) {
        ItemDraft itemDraft = tableItemDraft.getSelectionModel().getSelectedItem();
        if (itemDraft != null) {
            if (mouseEvent.getClickCount() >= 2) {
                showItem(itemDraft);
            }
        }
    }

    private void showItem(ItemDraft itemDraft) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market/fxml/itemDraft.fxml"));
            Parent root = null;
            root = (Parent) loader.load();
            ControllerItemDraft ctrl = loader.getController();
            double rest=0;
            if (payment==null)
                rest=totalApayer;
            else
                rest=((Draft)payment.getPayementItem()).getRest();

            ctrl.setData(this,itemDraft,rest);
            Scene newScene = new Scene(root);
            newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setScene(newScene);
            newStage.getScene().getStylesheets().add("Mycss.css");
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemDraft getItemDraft() {
        return itemDraft;
    }

    public void setItemDraft(ItemDraft itemDraft) {
        this.itemDraft = itemDraft;
        newStage.close();
    }
    public void updateTable(){
        if (payment!=null){
            Draft df=(Draft)payment.getPayementItem();
            listItemDraft.setAll(df.getItemsDraft());
            tableItemDraft.setItems(listItemDraft);
        }
    }
    public void addNewItem(ActionEvent actionEvent) {
        showItem(null);
        if (itemDraft!=null){
            Draft draft=(Draft) payment.getPayementItem();
            itemDraftManagment.add(draft,itemDraft);
            draftManagment.update(draft,totalApayer);
            iniWindow();
        }
        updateTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date=new Date();
        dateDraft.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
